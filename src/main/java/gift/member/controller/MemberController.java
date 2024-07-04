package gift.member.controller;

import gift.member.domain.LoginResponseDTO;
import java.util.HashMap;
import java.util.Map;
import gift.member.domain.Member;
import gift.member.service.MemberService;
import gift.member.util.AuthenticationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/token")
    public LoginResponseDTO login(@RequestBody Member member) {
        //ResponseEntity<Map<String, String>>
        String email = member.getEmail();
        String password = member.getPassword();

        return memberService.authenticate(email, password);
        //Map<String, String> response = new HashMap<>();
        //response.put("accessToken", token);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String, String>> handleAuthenticationFailedException(AuthenticationFailedException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
