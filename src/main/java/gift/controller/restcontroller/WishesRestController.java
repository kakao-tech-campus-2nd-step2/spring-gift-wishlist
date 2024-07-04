package gift.controller.restcontroller;

import gift.common.annotation.LoginMember;
import gift.controller.dto.request.WishRequest;
import gift.model.Member;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wishes")
public class WishesRestController {
    @PostMapping("")
    public ResponseEntity<String> updateWishes(@Valid @RequestBody WishRequest request,
                                               @LoginMember Long memberId) {
        System.out.println(memberId);
        return ResponseEntity.ok().body("success");
    }
}
