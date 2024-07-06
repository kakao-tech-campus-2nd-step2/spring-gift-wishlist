package gift.controller;

import gift.common.exception.ProductNotFoundException;
import gift.common.validation.LoginUser;
import gift.controller.dto.request.WishRequest;
import gift.model.Product;
import gift.model.User;
import gift.model.Wish;
import gift.model.repository.ProductRepository;
import gift.model.repository.WishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishes")
public class WishController {
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;

    public WishController(ProductRepository productRepository, WishRepository wishRepository) {
        this.productRepository = productRepository;
        this.wishRepository = wishRepository;
    }

    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWish(@PathVariable("productId") Long productId,
                        @RequestBody WishRequest wishRequest,
                        @LoginUser User loginUser
    ) {
        if (!wishRequest.productId().equals(productId)) {
            throw new IllegalArgumentException("요청 URL과 상품 ID가 일치하지 않습니다.");
        }

        Product product = productRepository.find(productId)
                .orElseThrow(() -> ProductNotFoundException.of(productId));

        Wish wish = wishRequest.toModel(loginUser.getId());
        wishRepository.save(wish);
    }
}
