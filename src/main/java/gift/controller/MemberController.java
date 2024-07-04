package gift.controller;

import gift.domain.Member;
import gift.service.MemberService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody Member member) {
        String generatedToken = memberService.generateMember(member);

        return new ResponseEntity<>("{\"token\": \"" + generatedToken + "\"}", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> signIn(@RequestBody Member member) {
        String generatedToken = memberService.authenticateMember(member);

        return new ResponseEntity<>("{\"token\": \"" + generatedToken + "\"}", HttpStatus.OK);
    }

}
