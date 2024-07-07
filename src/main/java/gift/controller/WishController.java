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
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addWish(@RequestBody WishRequest wishRequest,
                        @LoginUser User loginUser
    ) {
        Product product = productRepository.find(wishRequest.productId())
                .orElseThrow(() -> ProductNotFoundException.of(wishRequest.productId()));

        Wish wish = wishRequest.toModel(loginUser.getId());
        wishRepository.save(wish);
    }

    @PutMapping("/{wishId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateWish(@PathVariable("wishId") Long wishId,
                           @RequestBody WishRequest wishRequest,
                           @LoginUser User loginUser
    ) {
        Wish wish = wishRepository.findByIdAndUserId(loginUser.getId(), wishId)
                .orElseThrow(() -> new IllegalArgumentException("해당 위시리스트를 찾을 수 없습니다."));

        if (!wish.getProductId().equals(wishRequest.productId())) {
            throw new IllegalArgumentException("위시리스트의 상품을 변경할 수 없습니다.");
        }

        Wish newWish = wishRequest.toModel(wishId, loginUser.getId());
        wishRepository.save(newWish);
    }
}
