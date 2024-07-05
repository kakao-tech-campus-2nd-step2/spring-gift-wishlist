package gift.controller;

import gift.dto.MemberDTO;
import gift.dto.MemberPasswordDTO;
import gift.service.MemberService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok().body(memberService.register(memberDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok().body(memberService.login(memberDTO));
    }

    @PostMapping("/password")
    public ResponseEntity<Map<String, String>> changePassword(
        @RequestHeader("Authorization") String token,
        @Valid @RequestBody MemberPasswordDTO memberPasswordDTO
    ) {
        String accessToken = token.substring(7);
        return ResponseEntity.ok().body(memberService.changePassword(accessToken, memberPasswordDTO));
    }
}
