package gift.service;

import gift.dto.MemberRequestDTO;
import gift.dto.MemberResponseDTO;
import gift.model.Member;
import gift.repository.MemberRepository;
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
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        Member member = new Member(null, memberDTO.email(), memberDTO.password());
        Member savedMember = memberRepository.create(member);
        return new MemberResponseDTO(savedMember.getId(), savedMember.getEmail());
    }

    // 로그인 (회원 검증)
    public MemberResponseDTO loginMember(MemberRequestDTO memberDTO) {
        Member member = memberRepository.findByEmail(memberDTO.email())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!member.getPassword().equals(memberDTO.password())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new MemberResponseDTO(member.getId(), member.getEmail());
    }
}
