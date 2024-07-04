package gift.controller;

import gift.domain.MemberRequest;
import gift.domain.MenuRequest;
import gift.service.JwtService;
import gift.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    MemberService memberService;
    JwtService jwtService;

    public MemberController(MemberService memberService, JwtService jwtService){
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> join(
            @RequestParam("id") String id,
            @RequestParam("passwd") String passwd
    ) {
        System.out.println(id);
        MemberRequest memberRequest = new MemberRequest(id,passwd);
        memberService.join(memberRequest);
        System.out.println("안녕");
        String jwt = jwtService.createJWT(id);
        System.out.println(jwt);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",jwt);

        return ResponseEntity.ok().headers(headers).body("success");
    }

    @PostMapping("/login")
    public void login(
            @RequestParam("id") String id,
            @RequestParam("passwd") String passwd
    ) {
        System.out.println(id);
        MemberRequest memberRequest = new MemberRequest(id,passwd);
        memberService.join(memberRequest);

    }

}
