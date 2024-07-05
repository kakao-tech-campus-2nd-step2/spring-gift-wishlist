package gift.service;
import gift.domain.Member;
import gift.dto.MemberDto;
import gift.dto.Token;
import gift.exception.MemberAlreadyExistsException;
import gift.exception.MemberNotExistsException;
import gift.exception.PasswordNotMatchedException;
import gift.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto find(String email) {
        Optional<Member> member =  memberRepository.findByEmail(email);
        member.orElseThrow(MemberNotExistsException::new);
        return MemberDto.of(member.get());
    }

    public MemberDto save(MemberDto member) {
        memberRepository.findByEmail(member.email()).ifPresent(p -> {
            throw new MemberAlreadyExistsException();
        });
        return MemberDto.of(memberRepository.save(member));
    }

    public Token login(MemberDto member) {
        var m = memberRepository.findByEmail(member.email());
        m.orElseThrow(MemberNotExistsException::new);
        if (!member.password().equals(m.get().password())) {
            throw new PasswordNotMatchedException();
        }
        String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
        String accessToken = Jwts.builder()
            .setSubject(member.email())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
        return new Token(accessToken);
    }
}
