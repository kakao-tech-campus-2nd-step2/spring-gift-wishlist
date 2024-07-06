package gift.service;

import gift.domain.member.Member;
import gift.domain.member.MemberRequest;
import gift.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member register(MemberRequest memberRequest) {
        if (memberRepository.findByEmail(memberRequest.email()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 가입된 이메일입니다");
        }
        return memberRepository.insert(memberRequest);
    }

    public String  login(MemberRequest memberRequest) {
        Optional<Member> memberOptional = memberRepository.findByEmailAndPassword(memberRequest);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
            return Jwts.builder().subject(String.valueOf(member.id()))
                .claim("name", member.email())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이메일 혹은 비밀번호가 일치하지 않습니다");
        }
    }
}
