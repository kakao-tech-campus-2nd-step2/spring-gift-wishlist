package gift.controller;

import gift.dto.WishProductRequest;
import gift.dto.WishProductResponse;
import gift.service.WishProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/wishes")
public class WishProductController {

    private final WishProductService wishProductService;

    public WishProductController(WishProductService wishProductService) {
        this.wishProductService = wishProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addWishProduct(@Valid @RequestBody WishProductRequest wishProductRequest, @RequestAttribute("memberId") Long memberId) {
        var wishProduct = wishProductService.addWishProduct(wishProductRequest, memberId);
        return ResponseEntity.created(URI.create("/api/wishes/" + wishProduct.id())).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WishProductResponse> updateWishProduct(@PathVariable Long id, @Valid @RequestBody WishProductRequest wishProductRequest) {
        var wishProduct = wishProductService.updateWishProduct(id, wishProductRequest);
        return ResponseEntity.ok(wishProduct);
    }

    @GetMapping
    public ResponseEntity<List<WishProductResponse>> getWishProducts() {
        var wishProducts = wishProductService.getWishProducts();
        return ResponseEntity.ok(wishProducts);
    }
}
