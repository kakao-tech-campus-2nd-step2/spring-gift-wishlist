package gift;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute Member member, Model model) {
        memberService.registerMember(member);
        model.addAttribute("message", "회원 가입이 완료되었습니다.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpServletResponse response) {
        Member member = memberService.findByEmail(email);
        if (member == null || !password.equals(member.getPassword())) {
            model.addAttribute("message", "잘못된 이메일 또는 비밀번호입니다.");
            return "login";
        }

        String token = generateToken(member.getId(), member.getEmail());
        response.setHeader("Authorization", "Bearer " + token);
        return "redirect:/admin/products";
    }

    private String generateToken(Long memberId, String email) {
        String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
        return Jwts.builder()
                .setSubject(memberId.toString())
                .claim("email", email)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1일 유효
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
