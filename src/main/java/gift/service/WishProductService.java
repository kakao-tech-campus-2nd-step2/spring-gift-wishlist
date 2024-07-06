package gift.service;

import gift.dto.WishProductRequest;
import gift.dto.WishProductResponse;
import gift.model.WishProduct;
import gift.repository.WishProductRepository;
import org.springframework.stereotype.Service;

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
        var product = productService.getProduct(wishProductRequest.productId());
        memberService.existsById(memberId);
        var wishProduct = createWishProductWithWishProductRequest(wishProductRequest, memberId);
        var savedWishProduct = wishProductRepository.save(wishProduct);
        return WishProductResponse.from(savedWishProduct, product);
    }

    private WishProduct createWishProductWithWishProductRequest(WishProductRequest wishProductRequest, Long memberId) {
        return new WishProduct(wishProductRequest.productId(), memberId, wishProductRequest.count());
    }
}
