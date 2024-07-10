package gift.controller;

import gift.model.Product;
import gift.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @GetMapping
    public ResponseEntity<List<Product>> getWishList(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(wishListService.getWishListByUsername(username).getProducts());
    }

    @PostMapping
    public ResponseEntity<Void> addProductToWishList(@RequestBody Product product, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        wishListService.addProductToWishList(username, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeProductFromWishList(@PathVariable Long productId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        wishListService.removeProductFromWishList(username, productId);
        return ResponseEntity.ok().build();
    }
}
