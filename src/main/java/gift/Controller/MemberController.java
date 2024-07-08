package gift.Controller;

import gift.Model.Member;
import gift.Model.MemberAccessToken;
import gift.Service.MemberAccessTokenProvider;
import gift.Service.MemberService;

import gift.Service.UserService;
import jakarta.validation.Valid;

import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;
    private final MemberAccessTokenProvider memberAccessTokenProvider;
    private final UserService userService;

    public MemberController(MemberService memberService, MemberAccessTokenProvider memberAccessTokenProvider, UserService userService){
        this.memberService = memberService;
        this.memberAccessTokenProvider  = memberAccessTokenProvider;
        this.userService = userService;

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
        userService.addUser(member);

        return "login";
    }

    @PostMapping("/api/login/check")
    public String checkMember(@Valid@ModelAttribute Member member, Model model) {

        Member checkMember;
        try {
            checkMember = memberService.getMemberByEmail(member.getEmail());
        }catch (EmptyResultDataAccessException e){
            throw new IllegalArgumentException();
        }
        if(checkMember.getPassword().equals(member.getPassword())){
            String token = memberAccessTokenProvider.createJwt(member.getEmail());
            model.addAttribute("token", new MemberAccessToken(token));
            return "token";// 토큰을 localStroage에 저장 후 header로 보냄
        }
        return "login";

    }
}
