package gift.service;

import gift.authentication.token.JwtProvider;
import gift.authentication.token.Token;
import gift.domain.Member;
import gift.domain.vo.Email;
import gift.domain.vo.Password;
import gift.repository.MemberRepository;
import gift.web.dto.request.LoginRequest;
import gift.web.dto.request.member.CreateMemberRequest;
import gift.web.dto.response.LoginResponse;
import gift.web.dto.response.member.CreateMemberResponse;
import gift.web.dto.response.member.ReadMemberResponse;
import gift.web.validation.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public MemberService(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    public CreateMemberResponse createMember(CreateMemberRequest request) {
        Member member = request.toEntity();
        return new CreateMemberResponse(memberRepository.save(member));
    }

    public ReadMemberResponse readMember(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. id: " + id));

        return ReadMemberResponse.fromEntity(member);
    }

    //todo: 이메일로 회원 조회 후 Password.matches()로 비밀번호 검증 -> 디테일한 예외를 알려줄 수 있다
    //이메일 조회가 안되면 -> 아이디 입력 잘못
    //패스워드가 맞지 않으면 -> 패스워드를 잘못 입력
    public LoginResponse login(LoginRequest request) {
        Email email = Email.from(request.getEmail());
        Password password = Password.from(request.getPassword());

        Member member = memberRepository.findByEmailAndPassword(email, password)
            .orElseThrow(InvalidCredentialsException::new);

        Token token = jwtProvider.generateToken(member);

        return new LoginResponse(token);
    }
}
