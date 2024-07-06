package gift.controller;


import gift.dto.LoginResultDto;
import gift.dto.MemberDto;
import gift.model.member.Member;
import gift.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequestMapping("/members")
@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewMember(@RequestBody MemberDto memberDto) {
        try{
            Member member = new Member(memberDto.email(),memberDto.password());
            memberService.registerNewMember(member);
            String token = memberService.returnToken(member);
            return ResponseEntity.ok().body(Collections.singletonMap("token", token));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberDto memberDto) {
        Member member = new Member(memberDto.email(),memberDto.password());
        LoginResultDto loginResultDto = memberService.loginMember(member);
        if(loginResultDto.isSuccess()){
            return ResponseEntity.ok().body(Collections.singletonMap("token", loginResultDto.getToken()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid email or password");
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

