package gift.controller;

import gift.annotation.LoginMember;
import gift.domain.Wishlist;
import gift.service.MemberService;
import gift.service.WishlistService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    private WishlistService wishlistService;
    private MemberService memberService;

    public WishlistController(WishlistService wishlistService, MemberService memberService) {
        this.wishlistService = wishlistService;
        this.memberService = memberService;
    }

//    @GetMapping
//    public ResponseEntity<List<Wishlist>> getAllWishlists(@LoginMember) {
//        Long memberId = memberService.getMemberIdByEmail();
//        return new ResponseEntity<>(wishlistService.getWishlistById(), HttpStatus.OK);
//    }

}
