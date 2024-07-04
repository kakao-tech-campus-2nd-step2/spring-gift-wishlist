package gift.service;

import gift.dto.MemberRequest;
import gift.dto.MemberResponse;
import gift.model.Member;
import gift.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Value("${SECRET_KEY}")
    private String secretKey;

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public MemberResponse register(MemberRequest memberRequest) {
        Member member = createMemberWithMemberRequest(memberRequest);
        Member savedMember = repository.save(member);
        String token = createAccessTokenWithMember(savedMember);
        return MemberResponse.from(token);
    }

    private Member createMemberWithMemberRequest(MemberRequest memberRequest) {
        return new Member(memberRequest.name(), memberRequest.email(), memberRequest.password());
    }

    private String createAccessTokenWithMember(Member member) {
        String accessToken = Jwts.builder()
                .subject(member.getId().toString())
                .claim("name", member.getName())
                .claim("role", member.getRole())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        return accessToken;
    }
}
