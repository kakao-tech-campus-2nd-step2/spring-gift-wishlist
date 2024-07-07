package gift.web;

import gift.service.wish.WishService;
import gift.web.dto.MemberDto;
import gift.web.dto.WishDto;
import gift.web.jwt.AuthUser;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping
    public ResponseEntity<List<WishDto>> getWishes(@AuthUser MemberDto member) {
        return new ResponseEntity<>(wishService.findAllWishes(member.email()), HttpStatus.OK);
    }

}
