package gift.api.member;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberDao memberDao;

    public MemberController(MemberService memberService, MemberDao memberDao) {
        this.memberService = memberService;
        this.memberDao = memberDao;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid MemberRequest memberRequest) {
        if (memberDao.hasMemberByEmail(memberRequest.email())) {
            throw new EmailAlreadyExistsException();
        }
        memberDao.insert(memberRequest);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Basic " + memberService.createToken(memberRequest));
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody MemberRequest memberRequest, @RequestHeader("Authorization") String token) {
        if (memberDao.hasMemberByEmailAndPassword(memberRequest)) {
            if (token.split(" ")[1].equals(memberService.createToken(memberRequest))) {
                return ResponseEntity.ok().build();
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
