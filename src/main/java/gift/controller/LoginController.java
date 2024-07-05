package gift.controller;

import gift.auth.JwtTokenProvider;
import gift.request.JoinRequest;
import gift.response.JoinResponse;
import gift.request.LoginRequest;
import gift.exception.InputException;
import gift.exception.LoginErrorException;
import gift.model.Member;
import gift.repository.MemberDao;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final MemberDao memberDao;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(MemberDao memberDao, JwtTokenProvider jwtTokenProvider) {
        this.memberDao = memberDao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/join")
    public ResponseEntity<JoinResponse> join(@RequestBody @Valid JoinRequest joinRequest,
        BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        Member member = new Member(joinRequest.email(), joinRequest.password());
        Member joinedMember = memberDao.insertMember(member);

        response.setHeader("Authorization",jwtTokenProvider.generateToken(joinedMember));

        return ResponseEntity.ok(new JoinResponse(joinRequest.email(), "회원가입이 완료되었습니다."));
    }

    @PostMapping("/api/login")
    public void login(@RequestBody @Valid LoginRequest loginRequest,
        BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        Member loginedMember = memberDao.getMemberByEmail(loginRequest.email());
        if(!loginedMember.login(loginRequest.email(), loginRequest.password())) {
            throw new LoginErrorException();
        }

        response.setHeader("Authorization",jwtTokenProvider.generateToken(loginedMember));

    }

}
