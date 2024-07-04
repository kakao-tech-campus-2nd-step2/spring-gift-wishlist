package gift.product.controller;

import gift.product.model.Member;
import gift.product.service.MemberService;
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

    @Autowired
    public ApiMemberController(
        MemberService memberService, MemberValidation memberValidation) {
        this.memberService = memberService;
        this.memberValidation = memberValidation;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> registerMember(@RequestBody Member member) {
        System.out.println("[ApiMemberController] registerMember()");
        memberService.registerMember(member);
        Map<String, String> response = new HashMap<>();
        response.put("token", memberService.generateToken(member));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Member member) {
        if(memberValidation.validateMember(member)) {
            Map<String, String> response = new HashMap<>();
            response.put("token", memberService.generateToken(member));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
