package gift.service;

import gift.dto.MemberRequest;
import gift.entity.Member;
import gift.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final Key secretKey;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String register(MemberRequest memberRequest) {
        String encodedPassword = passwordEncoder.encode(memberRequest.getPassword());
        Member member = new Member();
        member.setEmail(memberRequest.getEmail());
        member.setPassword(encodedPassword);
        memberRepository.save(member);

        return generateToken(member);
    }

    public String authenticate(MemberRequest memberRequest) {
        Optional<Member> optionalMember = memberRepository.findByEmail(memberRequest.getEmail());
        if (optionalMember.isPresent() && passwordEncoder.matches(memberRequest.getPassword(), optionalMember.get().getPassword())) {
            return generateToken(optionalMember.get());
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    private String generateToken(Member member) {
        return Jwts.builder()
                .setSubject(member.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1일
                .signWith(secretKey)
                .compact();
    }
}
