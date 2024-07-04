package gift.Controller;

import gift.Model.Member;
import gift.Model.MemberAccessToken;
import gift.Service.MemberAccessTokenProvider;
import gift.Service.MemberService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/api/login")
    public String getLogin(Model model) {
        model.addAttribute("member",new Member());
        return "login";
    }
    @GetMapping("/api/signup")
    public String getSignup(Model model) {
        model.addAttribute("member",new Member());
        return "signup";
    }

    @PostMapping("/api/signup")
    public String signupMember(@Valid@ModelAttribute Member member) {
        memberService.signupMember(member);
        return "login";
    }

    @PostMapping("/api/login/check")
    public ResponseEntity<MemberAccessToken> checkMember(@Valid@ModelAttribute Member member) {
        Member checkMember;
        try {
            checkMember = memberService.getMemberByEmail(member.getEmail());
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if(checkMember.getPassword().equals(member.getPassword())){
            return ResponseEntity.ok().body(new MemberAccessToken(MemberAccessTokenProvider.createJwt(member.getEmail())));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
