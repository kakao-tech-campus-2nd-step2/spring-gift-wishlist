package gift.service;

import gift.entity.Member;
import gift.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey; // 애플리케이션 설정 파일에서 주입된다.

    public String generateToken(Member member) {
        return Jwts.builder()
                .setSubject(Long.toString(member.id()))
                .claim("email", member.email())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

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
        if (member != null && passwordEncoder.matches(password, member.password())) {
            return member;
        }
        return null;
    }
}