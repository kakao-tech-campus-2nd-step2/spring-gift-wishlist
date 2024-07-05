package gift.service;

import gift.domain.model.ProductDto;
import gift.domain.model.WishResponseDto;
import gift.domain.repository.WishRepository;
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

    public List<WishResponseDto> getWishesByUserEmail(String email) {
        return wishRepository.getWishesByUserEmail(email);
    }

    public ProductDto addWish(String email, Long productId) {
        productService.validateExistProductId(productId);
        wishRepository.addWish(email, productId);
        return productService.getProduct(productId);
    }
}
