package gift.service.member;

import gift.domain.member.Member;
import gift.domain.member.MemberRepository;
import gift.web.dto.MemberDto;
import gift.web.dto.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberDto> getMembers() {
        return List.copyOf(memberRepository.findAll()
                .stream()
                .map(MemberDto::from)
                .toList()
                );
    }

    public MemberDto getMemberByEmail(String email) {
        return MemberDto.from(memberRepository.getMemberByEmail(email));
    }

    public void createMember(MemberDto memberDto) {
        MemberDto.from(memberRepository.insertMember(MemberDto.toEntity(memberDto)));
    }

    public MemberDto updateMember(String email, MemberDto memberDto) {
        memberRepository.getMemberByEmail(email);
        Member newMember = MemberDto.toEntity(memberDto);
        memberRepository.updateMember(newMember);
        return MemberDto.from(newMember);
    }

    public void deleteMember(String email) {
        memberRepository.getMemberByEmail(email);
        memberRepository.deleteMember(email);
    }

    public Token createJWT(MemberDto memberDto) {
        String secretKey = "6a6115fd7149b725c2ce38080aa88276d9113e949692aa68ed9fc36259f3f268";
        return new Token(Jwts.builder()
            .claim("email", memberDto.email())
            .claim("password", memberDto.password())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact());
    }
}