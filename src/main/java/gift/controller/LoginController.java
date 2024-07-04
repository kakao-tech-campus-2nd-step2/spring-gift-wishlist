package gift.controller;

import gift.dto.JoinRequest;
import gift.dto.JoinResponse;
import gift.dto.LoginRequest;
import gift.exception.InputException;
import gift.exception.LoginErrorException;
import gift.model.Member;
import gift.repository.MemberDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import javax.security.auth.login.CredentialNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final MemberDao memberDao;

    public LoginController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @PostMapping("/api/join")
    public ResponseEntity<JoinResponse> join(@RequestBody @Valid JoinRequest joinRequest,
        BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        Member joinMember = new Member(joinRequest.email(), joinRequest.password());
        memberDao.insertMember(joinMember);

        HttpSession session = request.getSession(true);
        session.setAttribute("member", joinMember);

        return ResponseEntity.ok(new JoinResponse(joinRequest.email(), "회원가입이 완료되었습니다."));
    }

    @PostMapping("/api/login")
    public void login(@RequestBody @Valid LoginRequest loginRequest,
        BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        Member loginedMember = memberDao.getMemberByEmail(loginRequest.email());
        if(!loginedMember.login(loginRequest.email(), loginRequest.password())) {
            throw new LoginErrorException();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("member", loginedMember);

    }

}
