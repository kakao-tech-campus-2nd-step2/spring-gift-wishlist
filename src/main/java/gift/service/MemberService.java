package gift.service;

import gift.dto.request.MemberRequest;
import gift.domain.Member;
import gift.exception.AccessDeniedException;
import gift.exception.InvalidCredentialsException;
import gift.exception.MemberNotFoundException;
import gift.repository.MemberJDBCRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberJDBCRepository memberRepository;

    @Autowired
    public MemberService(MemberJDBCRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String register(MemberRequest memberRequest) {
        Optional<Member> oldMember = memberRepository.findByEmail(memberRequest.getEmail());
        if (oldMember.isPresent()) {
            throw new AccessDeniedException("이미 등록된 이메일입니다.");
        }
        Member member = new Member(memberRequest.getEmail(), memberRequest.getPassword());
        memberRepository.save(member);
        return generateJwtToken(member);
    }

    public String authenticate(MemberRequest memberRequest) {
        Member member = memberRepository.findByEmail(memberRequest.getEmail())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        if (!memberRequest.getPassword().equals(member.getPassword())) {
            throw new InvalidCredentialsException("잘못된 비밀번호입니다.");
        }
        return generateJwtToken(member);
    }

    private String generateJwtToken(Member member) {
        SecretKey secretKey = Keys.hmacShaKeyFor(member.getSecretKey().getBytes());
        return Jwts.builder()
                .setSubject(member.getEmail())
                .signWith(secretKey)
                .compact();
    }
}
