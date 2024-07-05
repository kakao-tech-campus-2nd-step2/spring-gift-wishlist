package gift.controller.wishlist;

import gift.dto.Product;
import gift.dto.response.ProductId;
import gift.dto.request.WishListRequest;
import gift.service.ProductService;
import gift.service.WishListService;
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

    @GetMapping("api/wishlist/{memberId}")
    public List<Product> getWishList(@PathVariable("memberId") Long memberId) {
        List<Long> productIdList = wishListService.getProductIdList(memberId);
        List<Product> productList = new ArrayList<>();
        for (Long l : productIdList) {
            productList.add(productService.getProduct(l));
        }
        return productList;
    }

    @PostMapping("api/wishlist")
    public ResponseEntity<String> addWishList(@RequestBody WishListRequest wishListRequest) {
        wishListService.addProductToWishList(wishListRequest.getMemberId(), wishListRequest.getProductId());
        return ResponseEntity.ok("성공");
    }

    @DeleteMapping("api/wishlist")
    public ResponseEntity<ProductId> deleteProductInWishList(@RequestBody WishListRequest wishListRequest) {
        Long deletedProductId = wishListService.deleteProductInWishList(wishListRequest.getMemberId(), wishListRequest.getProductId());
        return ResponseEntity.ok(new ProductId(deletedProductId));
    }

}
