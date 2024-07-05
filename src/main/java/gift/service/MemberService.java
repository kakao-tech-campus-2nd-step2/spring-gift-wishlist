package gift.service;

import gift.entity.Member;
import gift.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 암호화 클래스 사용
    }

    // 회원가입 처리
    public Member register(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member(email, encodedPassword);
        return memberRepository.save(member);
    }

    // 로그인 처리
    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member != null && passwordEncoder.matches(password, member.getPassword())) {
            return member;
        }
        return null;
    }
}