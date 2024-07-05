package gift.service;

import gift.domain.model.Product;
import gift.domain.model.ProductDto;
import gift.domain.model.User;
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

    public List<Product> getProductsByUserEmail(User user) {
        return wishRepository.getProductsByUserEmail(user.getEmail());
    }

    public ProductDto addWish(String email, Long productId) {
        productService.validateExistProductId(productId);
        if (wishRepository.isExistWish(email, productId)) {
            throw new DuplicateWishItemException("이미 위시리스트에 존재하는 상품입니다.");
        }
        wishRepository.addWish(email, productId);
        return productService.getProduct(productId);
    }

    public void deleteProduct(String email, Long productId) {
        productService.validateExistProductId(productId);
        if (!wishRepository.isExistWish(email, productId)) {
            throw new NoSuchWishException("위시리스트에 존재하지 않는 상품입니다.");
        }
        wishRepository.deleteWish(email, productId);
    }
}
