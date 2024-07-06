package gift.service;

import gift.domain.Member;
import gift.dto.JwtDto;
import gift.dto.MemberDto;
import gift.repository.MemberRepository;
import gift.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public JwtDto register(MemberDto memberDto) {

        Member member = new Member(memberDto.getEmail(), memberDto.getPassword() );
        memberRepository.save(new Member(memberDto.getEmail(), memberDto.getPassword() ));

        return jwtUtil.generateToken(member);
    }

    public JwtDto login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member != null && member.getPassword().equals(password)) {
            return jwtUtil.generateToken(member);
        }
        return null;
    }

}
