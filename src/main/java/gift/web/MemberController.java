package gift.web;

import gift.web.dto.Member;
import gift.web.dto.Token;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Member member) {
        HttpHeaders headers = new HttpHeaders();
        Token token = memberService.createJWT(member);
        headers.add("Authorization", "Bearer " + token);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(token);
    }
}
