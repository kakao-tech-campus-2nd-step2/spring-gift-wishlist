package gift.api.wishlist;

import gift.api.member.Member;
import gift.global.LoginMember;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishes")
public class WishListController {

    private final WishListDao wishListDao;

    public WishListController(WishListDao wishListDao) {
        this.wishListDao = wishListDao;
    }

    @GetMapping()
    public ResponseEntity<List<WishList>> getItems(@LoginMember Member member) {
        return ResponseEntity.ok().body(wishListDao.getAllWishes(member));
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody WishListRequestDto wishListRequestDto, @LoginMember Member member) {
        wishListDao.insert(wishListRequestDto, member);
        return ResponseEntity.created(URI.create("/api/wishes/" + member.getId())).build();
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody WishListRequestDto wishListRequestDto, @LoginMember Member member) {
        if (wishListRequestDto.quantity() == 0) {
            wishListDao.delete(wishListRequestDto, member);
            return ResponseEntity.noContent().build();
        }
        wishListDao.update(wishListRequestDto, member);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestBody WishListRequestDto wishListRequestDto, @LoginMember Member member) {
        wishListDao.delete(wishListRequestDto, member);
        return ResponseEntity.noContent().build();
    }
}
