package gift.api;

import gift.annotation.LoginMember;
import gift.application.WishesService;
import gift.dto.ProductResponse;
import gift.dto.WishRequest;
import gift.util.ProductMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishes")
public class WishesController {

    private final WishesService wishesService;

    public WishesController(WishesService wishesService) {
        this.wishesService = wishesService;
    }

    @GetMapping
    public List<ProductResponse> getAllWishes(@LoginMember Long memberId) {
        return wishesService.getWishlistOfMember(memberId)
                .stream()
                .map(ProductMapper::toResponseDto)
                .toList();
    }

    @PostMapping
    public void addWish(@LoginMember Long memberId, @RequestBody WishRequest request) {
        wishesService.addProductToWishlist(memberId, request.productId());
    }

    @DeleteMapping("/{productId}")
    public void removeWish(@LoginMember Long memberId, @PathVariable("productId") Long productId) {
        wishesService.removeProductFromWishlist(memberId, productId);
    }

}
