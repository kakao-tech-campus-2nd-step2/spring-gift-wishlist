package gift.controller;


import gift.dto.MemberDto;
import gift.model.member.Member;
import gift.service.MemberService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequestMapping("/members")
@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerNewMember(@RequestBody MemberDto memberDto) {
        Member member = new Member(memberDto.email(),memberDto.password());
        String token = memberService.registerNewMember(member);
        return ResponseEntity.ok().body(Collections.singletonMap("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginMember(@RequestBody MemberDto memberDto) throws AuthenticationException {
        Member member = new Member(memberDto.email(),memberDto.password());
        String token = memberService.loginMember(member);
        return ResponseEntity.ok().body(Collections.singletonMap("token", token));
    }

    @GetMapping("/register")
    public String moveToRegister() {
        return "registerMember";
    }

    @GetMapping("/login")
    public String moveToLogin() {
        return "loginMember";
    }
}

