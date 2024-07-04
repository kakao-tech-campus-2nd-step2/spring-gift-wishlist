package gift.service;

import gift.dto.AuthResponse;
import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.exception.DuplicatedEmailException;
import gift.exception.InvalidLoginInfoException;
import gift.exception.UnauthorizedAccessException;
import gift.model.Member;
import gift.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
public class MemberService {

    @Value("${SECRET_KEY}")
    private String secretKey;

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        emailValidation(registerRequest.email());
        var member = createMemberWithMemberRequest(registerRequest);
        var savedMember = repository.save(member);
        var token = createAccessTokenWithMember(savedMember);
        return AuthResponse.from(token);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        var member = repository.findByEmail(loginRequest.email());
        loginInfoValidation(member, loginRequest.password());
        var token = createAccessTokenWithMember(member);
        return AuthResponse.from(token);
    }

    public void deleteUser(Long id, String token) {
        deleteValidation(id, token);
        repository.deleteById(id);
    }

    private void emailValidation(String email) {
        if (repository.existsByEmail(email)) {
            throw new DuplicatedEmailException("이미 존재하는 이메일입니다.");
        }
    }

    private void loginInfoValidation(Member member, String password) {
        if (!member.getPassword().equals(password)) {
            throw new InvalidLoginInfoException("로그인 정보가 유효하지 않습니다.");
        }
    }

    private void deleteValidation(Long id, String token){
        var tokenId = getUserIdWithToken(token);
        if(!id.equals(tokenId)){
            throw new UnauthorizedAccessException("인가되지 않은 요청입니다.");
        }
    }

    private Member createMemberWithMemberRequest(RegisterRequest registerRequest) {
        return new Member(registerRequest.name(), registerRequest.email(), registerRequest.password());
    }

    private String createAccessTokenWithMember(Member member) {
        var token = Jwts.builder()
                .subject(member.getId().toString())
                .claim("name", member.getName())
                .claim("role", member.getRole())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        return token;
    }

    private Long getUserIdWithToken(String token){
        var verifyKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        var id = Jwts.parser()
                .verifyWith(verifyKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.parseLong(id);
    }
}
