package gift.controller;

import gift.config.LoginMember;
import gift.domain.member.Member;
import gift.request.WishListProductRequest;
import gift.response.ProductResponse;
import gift.service.WishlistService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/wishlists/products")
@RestController
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> productList(@LoginMember Member member) {
        List<ProductResponse> products = wishlistService.getProducts(member.getId());

        return ResponseEntity.ok()
            .body(products);
    }

    @PostMapping
    public ResponseEntity<Void> productAdd(@LoginMember Member member,
        @RequestBody @Valid WishListProductRequest request) {
        wishlistService.addProduct(member.getId(), request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED)
            .build();
    }

}
