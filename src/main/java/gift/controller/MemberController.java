package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.MemberDto;
import gift.classes.Exceptions.EmailAlreadyExistsException;
import gift.classes.RequestState.RequestStateDTO;
import gift.classes.RequestState.RequestStatus;
import gift.classes.RequestState.SecureRequestStateDTO;
import gift.services.MemberService;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody MemberDto memberDto) {
        try {
            memberService.register(memberDto);
        }
        catch(RegisteringEmailIsAlreadyExists e) {
            return ResponseEntity.badRequest().body(new RequestStateDTO(
                RequestStatus.failed,
                e.getMessage()
            ));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseEntity.ok().body(new RequestStateDTO(
            RequestStatus.success,
            null
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto) {
        String token = memberService.login(memberDto);
        return ResponseEntity.ok().body(new SecureRequestStateDTO(
            RequestStatus.success,
            null,
            token
        ));
    }
}
