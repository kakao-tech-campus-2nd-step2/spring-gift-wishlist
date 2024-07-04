package gift.product.controller;

import gift.product.model.Product;
import gift.product.service.AdminProductService;
import gift.product.service.WishListService;
import gift.product.validation.ProductValidation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class ApiWishListController {

    private final WishListService wishListService;
    private final AtomicLong idCounter = new AtomicLong();

    @Autowired
    public ApiWishListController(
        WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Product>> showProductList(@PathVariable String email) {
        System.out.println("[ApiWishListController] showProductList()");
        List<Product> productList = new ArrayList<>(wishListService.getAllProducts(email));
        return ResponseEntity.ok(productList);
    }
}
