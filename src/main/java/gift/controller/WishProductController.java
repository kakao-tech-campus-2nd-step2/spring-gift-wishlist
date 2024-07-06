package gift.controller;

import gift.dto.WishProductRequest;
import gift.service.WishProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/wishes")
public class WishProductController {

    private final WishProductService wishProductService;

    public WishProductController(WishProductService wishProductService) {
        this.wishProductService = wishProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@Valid @RequestBody WishProductRequest wishProductRequest, @RequestAttribute("memberId") Long memberId) {
        var wishProduct = wishProductService.addWishProduct(wishProductRequest, memberId);
        return ResponseEntity.created(URI.create("/api/wishes/" + wishProduct.id())).build();
    }
}
