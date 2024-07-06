package gift.Login.controller;

import gift.Login.model.LoginRequest;
import gift.Login.model.Member;
import gift.Login.model.ResponseToken;
import gift.Login.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseToken> register(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Member existingMember = memberService.findMemberByEmail(email);
        if (existingMember != null) {
            return ResponseEntity.status(409).build();
        }

        Member newMember = memberService.registerMember(email, password);
        String token = memberService.generateToken(newMember);
        ResponseToken responseToken = new ResponseToken(token);
        return ResponseEntity.ok(responseToken);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseToken> login(@RequestBody LoginRequest request) {
        String token = memberService.login(request.getEmail(), request.getPassword());
        if (token != null) {
            ResponseToken responseToken = new ResponseToken(token);
            return ResponseEntity.ok(responseToken);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
