package gift.controller;


import gift.dto.MemberDto;
import gift.model.member.Member;
import gift.model.product.Product;
import gift.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ResponseEntity<Map<String, String>> loginMember(@RequestBody MemberDto memberDto) {
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

