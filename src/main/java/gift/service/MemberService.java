package gift.service;

import gift.dao.MemberDao;
import gift.model.member.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDao memberDao;
    private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public void registerNewMember(Member member){
        memberDao.insertMemeber(member);
    }

    public boolean loginMember(Member member){
        Member registeredMember = memberDao.selectMember(member.email());
        if (registeredMember.equals(member)) {
            return true;
        }
        return false;
    }

    public String generateToken(String email) {
        Member member = memberDao.selectMember(email);

        String token = Jwts.builder()
                .setSubject(member.email().toString())
                .claim("role", member.role())
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    /*public void updateMember(Member member){
        memberDao.updateMember(member);
    }

    public void selectAllMembers(){
        memberDao.selectAllMembers();
    } */
}
