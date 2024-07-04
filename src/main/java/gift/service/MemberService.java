package gift.service;

import gift.dto.AuthResponse;
import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.exception.DuplicatedEmailException;
import gift.exception.InvalidLoginInfoException;
import gift.exception.UnauthorizedAccessException;
import gift.model.Member;
import gift.repository.MemberRepository;
import gift.util.AuthServiceUtils;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        emailValidation(registerRequest.email());
        var member = createMemberWithMemberRequest(registerRequest);
        var savedMember = repository.save(member);
        var token = AuthServiceUtils.createAccessTokenWithMember(savedMember);
        return AuthResponse.from(token);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        var member = repository.findByEmail(loginRequest.email());
        loginInfoValidation(member, loginRequest.password());
        var token = AuthServiceUtils.createAccessTokenWithMember(member);
        return AuthResponse.from(token);
    }

    public void deleteMember(Long id, String token) {
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
        var memberIdWithToken = AuthServiceUtils.getMemberIdWithToken(token);
        if(!id.equals(memberIdWithToken)){
            throw new UnauthorizedAccessException("인가되지 않은 요청입니다.");
        }
    }

    private Member createMemberWithMemberRequest(RegisterRequest registerRequest) {
        return new Member(registerRequest.name(), registerRequest.email(), registerRequest.password());
    }
}
