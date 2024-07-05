package gift.service;

import gift.exception.ForbiddenException;
import gift.domain.Member;
import gift.repository.MemberDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void registerMember(String email, String password) {
//        if (memberDao.findByEmail(email) != null) {
//            System.out.println("here");
//            throw new RuntimeException("Email already registered");
//        }

        Member newMember = new Member(email, password);
        memberDao.save(newMember);
    }

    public String login(String email, String password) {
        Member member = memberDao.findByEmail(email);
        if (member == null || !password.equals(member.getPassword())) {
            throw new ForbiddenException("사용자 없거나 비밀번호 틀림");
        }

        String accessToken = generateJwtToken(member);
        return accessToken;
    }

    private String generateJwtToken(Member member) {
        String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
        String accessToken = Jwts.builder()
            .setSubject(member.getId().toString())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();

        return accessToken;
    }
}
