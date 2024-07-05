package gift.product.controller;

import gift.product.model.WishProduct;
import gift.product.model.WishProduct2;
import gift.product.service.WishListService;
import gift.product.util.CertifyUtil;
import gift.product.validation.WishListValidation;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class ApiWishListController {

    private final WishListService wishListService;
    private final AtomicLong idCounter = new AtomicLong();
    private final CertifyUtil certifyUtil;
    private final WishListValidation wishListValidation;

    @Autowired
    public ApiWishListController(WishListService wishListService, CertifyUtil certifyUtil, WishListValidation wishListValidation) {
        this.wishListService = wishListService;
        this.certifyUtil = certifyUtil;
        this.wishListValidation = wishListValidation;
    }

    @GetMapping()
    public ResponseEntity<List<WishProduct2>> showProductList(HttpServletRequest request, @RequestBody Map<String, Long> requestBody) {
        System.out.println("[ApiWishListController] showProductList()");

        String token = certifyUtil.checkAuthorization(request.getHeader("Authorization"));

        List<WishProduct2> productList = new ArrayList<>(wishListService.getAllProducts(certifyUtil.getEmailByToken(token)));
        return ResponseEntity.ok(productList);
    }

    @PostMapping()
    public ResponseEntity<String> registerWishProduct(HttpServletRequest request, @RequestBody Map<String, Long> requestBody) {
        System.out.println("[ApiWishListController] registerWishProduct()");

        String token = certifyUtil.checkAuthorization(request.getHeader("Authorization"));
        if(token == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");

        if(!wishListValidation.isExistsProduct(requestBody.get("productId")))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not exists product.");

        wishListService.registerWishProduct(new WishProduct(idCounter.incrementAndGet(), requestBody.get("productId"), Math.toIntExact(requestBody.get("count")), certifyUtil.getEmailByToken(token)));
        return ResponseEntity.status(HttpStatus.CREATED).body("WishProduct registered successfully");
    }

    @PutMapping()
    public ResponseEntity<String> updateCountWishProduct(HttpServletRequest request, @RequestBody Map<String, Long> requestBody) {
        System.out.println("[ApiWishListController] updateCountWishProduct()");

        String token = certifyUtil.checkAuthorization(request.getHeader("Authorization"));
        if(token == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");

        if(!wishListValidation.isRegisterProduct(requestBody.get("productId"), certifyUtil.getEmailByToken(token)))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not exists product in wishlist");

        if(requestBody.get("count") == 0)
            return deleteWishProduct(request, requestBody);

        wishListService.updateCountWishProduct(requestBody.get("productId"), Math.toIntExact(requestBody.get("count")), certifyUtil.getEmailByToken(token));
        return ResponseEntity.status(HttpStatus.CREATED).body("update WishProduct Count successfully");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteWishProduct(HttpServletRequest request, @RequestBody Map<String, Long> requestBody) {
        System.out.println("[ApiWishListController] deleteWishProduct()");

        String token = certifyUtil.checkAuthorization(request.getHeader("Authorization"));
        if(token == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");

        wishListService.deleteWishProduct(requestBody.get("productId"), certifyUtil.getEmailByToken(token));
        return ResponseEntity.status(HttpStatus.CREATED).body("delete WishProduct successfully");
    }
}
