package gift.product.service;

import gift.product.dto.JwtResponse;
import gift.product.dto.MemberDto;
import gift.product.model.Member;
import gift.product.repository.AuthRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void register(MemberDto memberDto) {
        validateMemberNotExist(memberDto);

        Member member = new Member(memberDto.email(), memberDto.password());
        authRepository.registerMember(member);
    }

    private void validateMemberNotExist(MemberDto memberDto) {
        boolean isMemberExist = authRepository.existsByEmail(memberDto.email());

        if (isMemberExist) {
            throw new IllegalArgumentException("이미 회원으로 등록된 이메일입니다.");
        }
    }

    public JwtResponse login(MemberDto memberDto) {
        validateMemberInfo(memberDto);

        String accessToken = Jwts.builder()
            .claim("email", memberDto.email())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();

        return new JwtResponse(accessToken);
    }

    private void validateMemberInfo(MemberDto memberDto) {
        boolean isMemberExist = authRepository.existsByEmail(memberDto.email());

        if (!isMemberExist) {
            throw new IllegalArgumentException("회원 정보가 존재하지 않습니다.");
        }

        Member member = authRepository.findMember(memberDto.email());

        if (!memberDto.password().equals(member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
