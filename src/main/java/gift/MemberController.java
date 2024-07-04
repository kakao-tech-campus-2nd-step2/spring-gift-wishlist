package gift;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @PostMapping("/register")
    public Member registerMember(@RequestBody Member member) {
        Member newMember = new Member(
            member.getEmail(),
            member.getPassword()
        );
        return newMember;
    }
}
