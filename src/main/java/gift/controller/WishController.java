package gift.controller;

import gift.LoginMember;
import gift.MemberDto;
import gift.RequestWishDto;
import gift.WishDto;
import gift.services.WishService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class WishController {
    public final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

//    Wish 추가
    @PostMapping("/add")
    public void addWish(@RequestBody RequestWishDto requestWishDto, @LoginMember MemberDto memberDto) {
        WishDto wishDto = new WishDto(memberDto.getMemberId(), requestWishDto.getProductId(), null);
        wishService.addWish(wishDto);
    }

//    Wishlist 조회
    @GetMapping
    public List<WishDto> getWishlistById(@LoginMember MemberDto memberDto) {
        return wishService.getWishListById(memberDto.getMemberId());
    }

//    Wish 삭제
    @GetMapping("/delete")
    public void deleteWish(@RequestBody RequestWishDto requestWishDto, @LoginMember MemberDto memberDto){
        wishService.deleteWish(memberDto.getMemberId(), requestWishDto.getProductId());
    }
}
