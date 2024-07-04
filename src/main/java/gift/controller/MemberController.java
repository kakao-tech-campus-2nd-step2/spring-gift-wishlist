package gift.controller;

import gift.dto.MemberRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/members")
public class MemberController {

    public MemberController() {
    }

    @PostMapping("/register")
    @ResponseBody
    public String memberRegister(@Valid @RequestBody MemberRequest memberRequest) {
        return "Hello";
    }
}
