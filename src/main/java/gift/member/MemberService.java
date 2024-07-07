package gift.member;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class MemberService {
    @Autowired
    private final MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
        memberDAO.create();
    }
    public String signUp(Member member) throws Exception {
        String token = getToken(member);
        memberDAO.insert(member, token);
        return token;
    }

    public String login(Member member) throws Exception {
            return memberDAO.selectTokenbyMember(member);
    }

    public boolean isValidToken(String token) {
        try {
            var ftoken = memberDAO.selectTokenbyToken(token);
            return !ftoken.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getToken(Member member){
        String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

        String accessToken = Jwts.builder()
                .setSubject(member.email())
                .claim("email", member.email())
                .claim("password", member.password())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        return accessToken;
    }

    public String stringToBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
}
