package gift.controller;

import gift.ArgumentResolver.LoginMember;
import gift.dto.MemberDTO;
import gift.dto.WishListDTO;
import gift.service.WishListService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/wishlist")
@RestController
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/{memberId}")
    public WishListDTO getWishList(@PathVariable int memberId, @LoginMember MemberDTO memberDTO) {
        return wishListService.getWishList(memberId);
    }

    //상품 추가
    @PostMapping("/{memberId}")
    public void addWishList(@PathVariable int memberId, @RequestBody WishListDTO wishListDTO) {
        wishListService.addProduct(memberId, wishListDTO.getProductId());
    }

    //상품 삭제
    @DeleteMapping("/{memberId}")
    public void deleteWishList(@PathVariable int memberId, @RequestBody WishListDTO wishListDTO) {
        wishListService.deleteProduct(memberId, wishListDTO.getProductId());
    }

    //상품 수정
    @PutMapping("/{memberId}")
    public void updateWishList(@PathVariable int memberId, @RequestBody WishListDTO wishListDTO) {
        wishListService.updateProduct(memberId, wishListDTO.getProductId(),
            wishListDTO.getProductValue());
    }


}
