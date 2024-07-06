package gift.Login.service;

import gift.Login.model.Member;
import gift.Login.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Value("${jwt.secret}")
    private String secretKey;

    public Member registerMember(String email, String password) {
        Member member = new Member(email, password);
        memberRepository.save(member);
        return member;
    }

    public String generateToken(Member member) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(member.getId().toString())
                .signWith(key)
                .compact();
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
