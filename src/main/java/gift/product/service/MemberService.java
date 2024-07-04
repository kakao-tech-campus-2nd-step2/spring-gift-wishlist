package gift.product.service;

import gift.product.dao.MemberDao;
import gift.product.model.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;
    private final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    @Autowired
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
        memberDao.createMemberTable();
    }

    public void registerMember(Member member) {
        memberDao.registerMember(member);
    }

    public String generateToken(Member member) {
        return Jwts.builder()
            .setSubject(member.getEmail())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

    public boolean isExistsMember(Member member) {
        return memberDao.isExistsMember(member);
    }
}
