package gift.web;

import gift.web.dto.Member;
import gift.web.dto.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    private final MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public Token createJWT(Member member) {
        String secretKey = "6a6115fd7149b725c2ce38080aa88276d9113e949692aa68ed9fc36259f3f268";
        return new Token(Jwts.builder()
            .setSubject(member.email())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact());
    }
}