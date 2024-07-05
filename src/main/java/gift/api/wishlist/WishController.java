package gift.api.wishlist;

import gift.api.member.Member;
import gift.global.LoginMember;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishes")
public class WishController {

    private final WishDao wishDao;

    public WishController(WishDao wishDao) {
        this.wishDao = wishDao;
    }

    @GetMapping()
    public ResponseEntity<List<Wish>> getItems(@LoginMember Member member) {
        return ResponseEntity.ok().body(wishDao.getAllWishes(member));
    }
}
