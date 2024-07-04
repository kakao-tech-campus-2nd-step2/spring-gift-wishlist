package gift.service;

import static gift.util.Constants.EMAIL_ALREADY_USED;
import static gift.util.Constants.EMAIL_NOT_FOUND;
import static gift.util.Constants.INVALID_AUTHORIZATION_HEADER;
import static gift.util.Constants.PASSWORD_MISMATCH;

import gift.dto.MemberRequestDTO;
import gift.dto.MemberResponseDTO;
import gift.exception.EmailAlreadyUsedException;
import gift.exception.ForbiddenException;
import gift.exception.InvalidTokenException;
import gift.model.Member;
import gift.repository.MemberRepository;
import gift.util.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
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
}
