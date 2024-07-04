package gift.controller;

import gift.domain.MemberRequest;
import gift.service.JwtService;
import gift.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        MemberRequest memberRequest = new MemberRequest(id,passwd);
        memberService.join(memberRequest);
        String jwt = jwtService.createJWT(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",jwt);

        return ResponseEntity.ok().headers(headers).body("success");
    }

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestParam("id") String id,
            @RequestParam("passwd") String passwd
    ) {
        MemberRequest memberRequest = new MemberRequest(id,passwd);
        MemberRequest memberGet = memberService.login(memberRequest);
        if(memberGet == null || !id.equals(memberGet.id())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("incorrect passwd or id");
        }
        else{
            String jwt = jwtService.createJWT(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","basic " + jwt);
            return ResponseEntity.ok().headers(headers).body("success");
        }
    }

    @PostMapping("/changePasswd")
    public ResponseEntity changePasswd(
            @RequestParam("id") String id,
            @RequestParam("passwd") String passwd
    ){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("changePasswd");
    }

    @PostMapping("/findPasswd")
    public ResponseEntity findPasswd(
            @RequestParam("id") String id,
            @RequestParam("passwd") String passwd
    ){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("findPasswd");
    }

}
