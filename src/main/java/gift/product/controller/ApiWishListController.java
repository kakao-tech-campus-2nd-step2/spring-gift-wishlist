package gift.product.controller;

import gift.product.model.Product;
import gift.product.model.WishProduct;
import gift.product.service.AdminProductService;
import gift.product.service.WishListService;
import gift.product.util.CertifyUtil;
import gift.product.validation.ProductValidation;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class ApiWishListController {

    private final WishListService wishListService;
    private final AtomicLong idCounter = new AtomicLong();
    private final CertifyUtil certifyUtil;

    @Autowired
    public ApiWishListController(WishListService wishListService, CertifyUtil certifyUtil) {
        this.wishListService = wishListService;
        this.certifyUtil = certifyUtil;
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Product>> showProductList(@PathVariable String email) {
        System.out.println("[ApiWishListController] showProductList()");
        List<Product> productList = new ArrayList<>(wishListService.getAllProducts(email));
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/{email}")
    public ResponseEntity<String> registerWishProduct(HttpServletRequest header, @RequestBody Map<String, Long> request, @PathVariable String email) {
        System.out.println("[ApiWishListController] registerWishProduct()");
        //String token = certifyUtil.getTokenFromRequest(header);
        //String email = certifyUtil.extractClaims(token).getSubject();

        wishListService.registerWishProduct(new WishProduct(idCounter.incrementAndGet(), request.get("productId"), Math.toIntExact(request.get("count")), email));
        return ResponseEntity.status(HttpStatus.CREATED).body("WishProduct registered successfully");
    }
}
