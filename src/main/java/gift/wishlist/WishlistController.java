package gift.wishlist;

import gift.login.LoginMember;
import gift.member.Member;
import gift.product.Product;
import gift.product.ProductDao;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WishlistController {

    private final WishlistDao wishlistDao;
    private final ProductDao productDao;

    public WishlistController(WishlistDao wishlistDao, ProductDao productDao) {
        this.wishlistDao = wishlistDao;
        this.productDao = productDao;
    }

    @PostMapping("/wishes")
    public void create(@RequestBody WishRequestDto request, @LoginMember Member member) {
        wishlistDao.insertWish(member,request.productId());
    }

    @GetMapping
    public List<Product> getAllWish() {
        List<Product> wishProducts = wishlistDao.findAllWish();
        return wishProducts;
    }

    @DeleteMapping
    public HttpEntity<String> deleteWish(@PathVariable Long id) {
        if (wishlistDao.findProductById(id).isEmpty()) {
            throw new NoSuchElementException("잘못된 접근입니다");
        }
        else {
            wishlistDao.deleteWish(id);
        }
        return ResponseEntity.ok("장바구니에서 제거되었습니다");
    }

}
