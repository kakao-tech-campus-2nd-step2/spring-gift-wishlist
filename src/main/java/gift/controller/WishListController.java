package gift.controller;

import gift.ArgumentResolver.LoginMember;
import gift.dto.MemberDTO;
import gift.dto.WishListDTO;
import gift.service.WishListService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/member/{memberid}/wishlist")
@RestController
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    //상품 리스트 조회
    @GetMapping
    public WishListDTO getWishList(@PathVariable int memberid, @LoginMember MemberDTO memberDTO) {
        return wishListService.getWishList(memberid);
    }

    //상품 추가
    @PutMapping
    public void addWishList(@PathVariable int memberid, @RequestBody WishListDTO wishListDTO) {
        wishListService.addNewProduct(memberid, wishListDTO.getProductId());
    }

    //상품 삭제
    @DeleteMapping
    public void deleteWishList(@PathVariable int memberid, @RequestBody WishListDTO wishListDTO) {
        wishListService.deleteProduct(memberid, wishListDTO.getProductId());
    }

    //상품 수정
    @PutMapping
    public void updateWishList(@PathVariable int memberid, @RequestBody WishListDTO wishListDTO) {
        wishListService.updateProduct(memberid, wishListDTO.getProductId(),
            wishListDTO.getProductValue());
    }


}
