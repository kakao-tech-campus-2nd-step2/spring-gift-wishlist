package gift.controller;

import gift.model.Product;
import gift.model.WishList;
import gift.service.WishListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    // 상품 조회
    @GetMapping("/{userid}")
    public List<WishList> getWishList(@PathVariable Long userId) {
        return wishListService.readWishList(userId);
    }

    // 위시리스트 추기
    @PostMapping("{userid}/add")
    public ResponseEntity<String> addWishList(@PathVariable Long userid,
        @RequestBody Product product) {
        wishListService.addProductToWishList(userid, product);
        return new ResponseEntity<>("Product added to wishlist", HttpStatus.CREATED);
    }


    // 위시리스트 특정 상품 삭제
    @DeleteMapping("/{userid}/delete/{wishId}")
    public ResponseEntity<String> removeProductFromWishList(@PathVariable Long userid,
        @PathVariable Long wishId) {
        wishListService.removeProductFromWishList(userid, wishId);
        return new ResponseEntity<>("Product removed from wishlist", HttpStatus.OK);
    }

    // 전체 위시리스트 삭제
    @DeleteMapping("/{userid}/deleteAll")
    public ResponseEntity<String> removeAllWishList(@PathVariable Long userid) {
        wishListService.removeWishList(userid);
        return new ResponseEntity<>("Wishlist deleted", HttpStatus.OK);
    }
}

