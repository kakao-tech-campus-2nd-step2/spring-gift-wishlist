package gift.product.controller;

import gift.product.dto.MemberDto;
import gift.product.dto.RegisterSuccessResponse;
import gift.product.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/members/register")
    public ResponseEntity<RegisterSuccessResponse> registerMember(@RequestBody MemberDto memberDto) {
        authService.register(memberDto);

        return ResponseEntity.ok(new RegisterSuccessResponse("회원가입이 완료되었습니다."));
    }
}
