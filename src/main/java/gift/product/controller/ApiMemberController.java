package gift.product.controller;

import gift.product.model.Member;
import gift.product.validation.LoginValidation;
import gift.product.service.MemberService;
import gift.product.util.CertifyUtil;
import gift.product.validation.MemberValidation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class ApiMemberController {

    private final MemberService memberService;
    private final MemberValidation memberValidation;
    private final CertifyUtil certifyUtil;
    private final LoginValidation loginValidation;

    @Autowired
    public ApiMemberController(
        MemberService memberService, MemberValidation memberValidation, CertifyUtil certifyUtil, LoginValidation loginValidation) {
        this.memberService = memberService;
        this.memberValidation = memberValidation;
        this.certifyUtil = certifyUtil;
        this.loginValidation = loginValidation;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Map<String, String> request) {
        System.out.println("[ApiMemberController] signUp()");

        Member member = certifyUtil.encryption(request.get("email"), request.get("password"));

        memberValidation.emailDuplicateCheck(member.getEmail());
        memberService.signUp(member);

        Map<String, String> response = new HashMap<>();
        response.put("token", certifyUtil.generateToken(member));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        System.out.println("[ApiMemberController] login()");

        Member member = certifyUtil.encryption(request.get("email"), request.get("password"));

        if(memberValidation.validateMember(member)) {
            Map<String, String> response = new HashMap<>();
            String token = certifyUtil.generateToken(member);
            response.put("token", token);
            loginValidation.logIn(member.getEmail(), token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
