package gift.controller;

import gift.dto.MemberDto;
import gift.dto.Token;
import gift.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<MemberDto> signUp(@RequestBody MemberDto member) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(member));
    }

    @PostMapping("/login")
    public ResponseEntity<Token> signIn(@RequestBody MemberDto member) {
        HttpHeaders headers = new HttpHeaders();
        Token token = memberService.login(member);
        headers.add("Authorization", token.toString());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(token);
    }
}
