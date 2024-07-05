package gift.controller;

import gift.auth.JwtTokenProvider;
import gift.request.JoinRequest;
import gift.response.JoinResponse;
import gift.request.LoginRequest;
import gift.exception.InputException;
import gift.exception.LoginErrorException;
import gift.model.Member;
import gift.repository.MemberDao;
import gift.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/api/join")
    public ResponseEntity<JoinResponse> join(@RequestBody @Valid JoinRequest joinRequest,
        BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        Member joinedMember = memberService.join(joinRequest.email(), joinRequest.password());
        response.setHeader("Authorization",jwtTokenProvider.generateToken(joinedMember));

        return ResponseEntity.ok(new JoinResponse(joinRequest.email(), "회원가입이 완료되었습니다."));
    }

    @PostMapping("/api/login")
    public void login(@RequestBody @Valid LoginRequest loginRequest,
        BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        Member loginedMember = memberService.login(loginRequest.email(), loginRequest.password());
        response.setHeader("Authorization",jwtTokenProvider.generateToken(loginedMember));

    }

}
