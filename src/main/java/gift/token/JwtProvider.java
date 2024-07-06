package gift.token;

import gift.member.Member;
import gift.member.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final MemberRepository memberRepository;

    public JwtProvider(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final String SECRET_KEY = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private final String PREFIX = "Bearer ";

    public String generateToken(Member member) {
        return PREFIX + Jwts.builder()
            .claim("email", member.email())
            .claim("password", member.password())
            .signWith(KEY)
            .compact();
    }

    public Member getMemberFromToken(String token) {
        Claims claims = Jwts.parser()
            .verifyWith((SecretKey) KEY)
            .build()
            .parseSignedClaims(token.replace(PREFIX, ""))
            .getPayload();
        Member member = new Member(
            claims.get("email", String.class),
            claims.get("password", String.class)
        );

        if (!memberRepository.existMemberByEmail(member.email())) {
            throw new IllegalArgumentException("Member does not exist");
        }
        member.isAuthentication(memberRepository.findMemberByEmail(member.email()));
        return member;
    }
}
