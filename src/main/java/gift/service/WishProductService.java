package gift.service;

import gift.dto.WishProductRequest;
import gift.dto.WishProductResponse;
import gift.model.WishProduct;
import gift.repository.WishProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishProductService {

    private final WishProductRepository wishProductRepository;
    private final ProductService productService;
    private final MemberService memberService;

    public WishProductService(WishProductRepository wishProductRepository, ProductService productService, MemberService memberService) {
        this.wishProductRepository = wishProductRepository;
        this.productService = productService;
        this.memberService = memberService;
    }

    public WishProductResponse addWishProduct(WishProductRequest wishProductRequest, Long memberId) {
        memberService.existsById(memberId);
        var wishProduct = createWishProductWithWishProductRequest(wishProductRequest, memberId);
        var savedWishProduct = wishProductRepository.save(wishProduct);
        return getWishProductResponseFromWishProduct(savedWishProduct);
    }

    public WishProductResponse updateWishProduct(Long id, WishProductRequest wishProductRequest) {
        var wishProduct = findWishProductWithId(id);
        var updatedWishProduct = updateWishProduct(wishProduct, wishProductRequest);
        return getWishProductResponseFromWishProduct(updatedWishProduct);
    }

    public List<WishProductResponse> getWishProducts() {
        return wishProductRepository.findAll()
                .stream()
                .map(this::getWishProductResponseFromWishProduct)
                .toList();
    }

    private WishProduct createWishProductWithWishProductRequest(WishProductRequest wishProductRequest, Long memberId) {
        return new WishProduct(wishProductRequest.productId(), memberId, wishProductRequest.count());
    }

    private WishProduct findWishProductWithId(Long id) {
        return wishProductRepository.findById(id);
    }

    private WishProduct updateWishProduct(WishProduct wishProduct, WishProductRequest wishProductRequest) {
        wishProduct.updateWishProduct(wishProductRequest.count());
        wishProductRepository.update(wishProduct);
        return wishProduct;
    }

    private WishProductResponse getWishProductResponseFromWishProduct(WishProduct wishProduct) {
        var product = productService.getProduct(wishProduct.getProductId());
        return WishProductResponse.from(wishProduct, product);
    }
}