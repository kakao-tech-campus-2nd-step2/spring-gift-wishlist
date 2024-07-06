package gift.controller.wishlist;

import gift.dto.ProductAmount;
import gift.dto.request.WishListAddRequest;
import gift.dto.request.WishListRequest;
import gift.dto.response.ProductIdResponse;
import gift.dto.response.WishedProductResponse;
import gift.service.ProductService;
import gift.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WishListController {

    private final WishListService wishListService;
    private final ProductService productService;

    public WishListController(WishListService wishListService, ProductService productService) {
        this.wishListService = wishListService;
        this.productService = productService;
    }

    @GetMapping("api/wishlist")
    public List<WishedProductResponse> getWishList(HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        List<ProductAmount> productIdList = wishListService.getProductIdsAndAmount(memberId);
        List<WishedProductResponse> responses = new ArrayList<>();
        for (ProductAmount productAmount : productIdList) {
            responses.add(new WishedProductResponse(productService.getProduct(productAmount.getProductId()), productAmount.getAmount()));
        }
        return responses;
    }

    @PostMapping("api/wishlist")
    public ResponseEntity<WishListAddRequest> addWishList(HttpServletRequest request, @RequestBody WishListAddRequest wishListRequest) {
        Long memberId = (Long) request.getAttribute("memberId");
        wishListService.addProductToWishList(memberId, wishListRequest.getProductId(), wishListRequest.getAmount());
        return ResponseEntity.ok(wishListRequest);
    }

    @DeleteMapping("api/wishlist")
    public ResponseEntity<ProductIdResponse> deleteProductInWishList(@RequestBody WishListRequest wishListRequest) {
        Long deletedProductId = wishListService.deleteProductInWishList(wishListRequest.getMemberId(), wishListRequest.getProductId());
        return ResponseEntity.ok(new ProductIdResponse(deletedProductId));
    }

}
