package gift.controller;

import gift.dto.JoinRequest;
import gift.dto.JoinResponse;
import gift.exception.InputException;
import gift.model.Member;
import gift.repository.MemberDao;
import jakarta.validation.Valid;
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

//    @PostMapping("/api/login")
//    public ResponseEntity<> login(@RequestBody @Valid LoginForm form,
//        BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            throw new MemberLoginFailException(bindingResult.getAllErrors());
//        }
//
//        Member loginedMember = memberDao.getMemberByEmail(form.email());
//        if (loginedMember == null) {
//
//        }
//
//
//    }

    @PostMapping("/api/join")
    public ResponseEntity<JoinResponse> join(@RequestBody @Valid JoinRequest joinRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        Member joinMember = new Member(joinRequest.email(), joinRequest.password());
        memberDao.insertMember(joinMember);

        return ResponseEntity.ok(new JoinResponse(joinRequest.email(), "회원가입이 완료되었습니다."));
    }

}
