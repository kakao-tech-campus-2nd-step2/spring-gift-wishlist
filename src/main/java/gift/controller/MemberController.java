package gift.controller;

import gift.dto.MemberRequest;
import gift.dto.MemberResponse;
import gift.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<MemberResponse> memberRegister(@Valid @RequestBody MemberRequest memberRequest) {
        var product = service.register(memberRequest);
        return ResponseEntity.ok(product);
    }
}
