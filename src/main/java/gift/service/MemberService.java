package gift.service;

import static gift.util.Constants.EMAIL_ALREADY_USED;
import static gift.util.Constants.EMAIL_NOT_FOUND;
import static gift.util.Constants.ID_NOT_FOUND;
import static gift.util.Constants.INVALID_AUTHORIZATION_HEADER;
import static gift.util.Constants.PASSWORD_MISMATCH;

import gift.dto.member.MemberRequestDTO;
import gift.dto.member.MemberResponseDTO;
import gift.exception.member.EmailAlreadyUsedException;
import gift.exception.member.ForbiddenException;
import gift.exception.member.InvalidTokenException;
import gift.model.Member;
import gift.repository.MemberRepository;
import gift.util.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 (회원 추가)
    public MemberResponseDTO registerMember(MemberRequestDTO memberDTO) {
        if (memberRepository.existsByEmail(memberDTO.email())) {
            throw new EmailAlreadyUsedException(EMAIL_ALREADY_USED);
        }

        Member member = new Member(null, memberDTO.email(), memberDTO.password());
        Member savedMember = memberRepository.create(member);

        String token = JWTUtil.generateToken(member.getEmail());
        return new MemberResponseDTO(savedMember.getId(), savedMember.getEmail(), token);
    }

    // 로그인 (회원 검증)
    public MemberResponseDTO loginMember(MemberRequestDTO memberDTO) {
        Member member = memberRepository.findByEmail(memberDTO.email())
            .orElseThrow(() -> new ForbiddenException(EMAIL_NOT_FOUND));

        if (!member.getPassword().equals(memberDTO.password())) {
            throw new ForbiddenException(PASSWORD_MISMATCH);
        }

        String token = JWTUtil.generateToken(member.getEmail());
        return new MemberResponseDTO(member.getId(), member.getEmail(), token);
    }

    // 토큰 검증
    public void validateToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException(INVALID_AUTHORIZATION_HEADER);
        }

        String token = authorizationHeader.substring(7);
        Claims claims = JWTUtil.validateToken(token);
        request.setAttribute("claims", claims);
    }

    // 모든 회원 조회
    public List<MemberResponseDTO> getAllMembers() {
        return memberRepository.findAll().stream()
            .map(MemberService::convertToDTO)
            .collect(Collectors.toList());
    }

    // ID로 회원 조회
    public MemberResponseDTO getMemberById(Long id) {
        return memberRepository.findById(id)
            .map(MemberService::convertToDTO)
            .orElseThrow(() -> new ForbiddenException(EMAIL_NOT_FOUND));
    }

    // 회원 수정
    public MemberResponseDTO updateMember(Long id, MemberRequestDTO memberDTO) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new ForbiddenException(EMAIL_NOT_FOUND));

        if (!member.getEmail().equals(memberDTO.email()) && memberRepository.existsByEmail(memberDTO.email())) {
            throw new EmailAlreadyUsedException(EMAIL_ALREADY_USED);
        }

        member.update(memberDTO.email(), memberDTO.password());
        Member updatedMember = memberRepository.update(member);
        return convertToDTO(updatedMember);
    }

    // 회원 삭제
    public void deleteMember(Long id) throws ForbiddenException {
        if (!memberRepository.existsById(id)) {
            throw new ForbiddenException(ID_NOT_FOUND);
        }
        memberRepository.delete(id);
    }

    // Mapper methods
    private static MemberResponseDTO convertToDTO(Member member) {
        return new MemberResponseDTO(member.getId(), member.getEmail(), null);
    }
}
