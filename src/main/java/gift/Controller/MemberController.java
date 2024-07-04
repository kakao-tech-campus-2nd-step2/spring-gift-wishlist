package gift.Controller;

import gift.Model.Member;
import gift.Model.MemberAccessToken;
import gift.Service.MemberService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
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

    @PostMapping("/api/login/check")
    public ResponseEntity<MemberAccessToken> getMember(@Valid@ModelAttribute Member member) {
        Member checkMember;
        MemberAccessToken memberAccessToken;
        try {
            checkMember = memberService.getMemberByEmail(member.getEmail());
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.ok().headers(new HttpHeaders()).body(new MemberAccessToken(false));
        }

        if(checkMember.getPassword().equals(member.getPassword())){
            return ResponseEntity.ok().headers(new HttpHeaders()).body(new MemberAccessToken(true));
        }
        return ResponseEntity.ok().headers(new HttpHeaders()).body(new MemberAccessToken(false));
    }
}
