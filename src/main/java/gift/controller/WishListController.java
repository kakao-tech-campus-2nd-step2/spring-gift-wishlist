package gift.controller;

import gift.authService.LoginUser;
import gift.model.WishList;
import gift.model.WishListDAO;
import gift.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WishListController 클래스는 위시리스트에 대한 RESTful API를 제공합니다.
 */
@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListDAO wishListDAO;

    @Autowired
    public WishListController(WishListDAO wishListDAO) {
        this.wishListDAO = wishListDAO;
    }

    /**
     * 새로운 위시리스트 항목을 생성합니다.
     *
     * @param productId 생성할 위시리스트 항목의 상품 ID
     * @param login     로그인된 사용자 정보
     * @return 생성된 위시리스트 항목
     */
    @PostMapping
    public ResponseEntity<WishList> createWishList(@RequestParam Long productId, @LoginUser Login login) {
        WishList newWishList = wishListDAO.createWishList(productId, login.getId());
        return ResponseEntity.ok(newWishList);
    }

    /**
     * 로그인된 사용자의 모든 위시리스트 항목을 조회합니다.
     *
     * @param login 로그인된 사용자 정보
     * @return 지정된 사용자의 모든 위시리스트 항목
     */
    @GetMapping
    public ResponseEntity<List<WishList>> getWishListsByUserId(@LoginUser Login login) {
        List<WishList> wishLists = wishListDAO.getWishListsByUserId(login.getId());
        return ResponseEntity.ok(wishLists);
    }

    /**
     * 모든 위시리스트 항목을 조회합니다.
     *
     * @return 모든 위시리스트 항목
     */
    @GetMapping("/all")
    public ResponseEntity<List<WishList>> getAllWishLists() {
        List<WishList> wishLists = wishListDAO.getAllWishLists();
        return ResponseEntity.ok(wishLists);
    }

    /**
     * 로그인된 사용자의 모든 위시리스트 항목을 삭제합니다.
     *
     * @param login 로그인된 사용자 정보
     * @return 삭제 결과
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteWishListsByUserId(@LoginUser Login login) {
        wishListDAO.deleteWishListsByUserId(login.getId());
        return ResponseEntity.noContent().build();
    }

    /**
     * 로그인된 사용자가 지정된 상품을 위시리스트에서 삭제합니다.
     *
     * @param productId 삭제할 상품의 ID
     * @param login     로그인된 사용자 정보
     * @return 삭제 결과
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteWishListByUserIdAndProductId(@PathVariable Long productId, @LoginUser Login login) {
        wishListDAO.deleteWishListByUserIdAndProductId(login.getId(), productId);
        return ResponseEntity.noContent().build();
    }
}
