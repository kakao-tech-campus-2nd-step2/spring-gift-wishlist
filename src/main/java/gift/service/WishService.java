package gift.service;

import gift.domain.model.ProductDto;
import gift.domain.model.WishResponseDto;
import gift.domain.model.WishUpdateRequestDto;
import gift.domain.repository.WishRepository;
import gift.exception.DuplicateWishItemException;
import gift.exception.NoSuchWishException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    private final WishRepository wishRepository;
    private final ProductService productService;

    public WishService(WishRepository wishRepository, ProductService productService) {
        this.wishRepository = wishRepository;
        this.productService = productService;
    }

    public List<WishResponseDto> getProductsByUserEmail(String email) {
        return wishRepository.getProductsByUserEmail(email);
    }

    public ProductDto addWish(String email, Long productId) {
        productService.validateExistProductId(productId);
        if (wishRepository.isExistWish(email, productId)) {
            throw new DuplicateWishItemException("이미 위시리스트에 존재하는 상품입니다.");
        }
        wishRepository.addWish(email, productId);
        return productService.getProduct(productId);
    }

    public void deleteWishProduct(String email, Long productId) {
        productService.validateExistProductId(productId);
        validateExistWishProduct(email, productId);
        wishRepository.deleteWishProduct(email, productId);
    }

    public void updateWishProduct(String email, WishUpdateRequestDto wishUpdateRequestDto) {
        productService.validateExistProductId(wishUpdateRequestDto.getProductId());
        validateExistWishProduct(email, wishUpdateRequestDto.getProductId());
        wishRepository.updateWishProduct(email, wishUpdateRequestDto);
    }

    public void validateExistWishProduct(String email, Long productId){
        if (!wishRepository.isExistWish(email, productId)) {
            throw new NoSuchWishException("위시리스트에 존재하지 않는 상품입니다.");
        }
    }
}
