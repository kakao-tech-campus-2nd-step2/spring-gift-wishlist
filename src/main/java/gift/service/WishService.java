package gift.service;

import gift.dto.WishRequest;
import gift.exception.product.ProductNotFoundException;
import gift.model.Product;
import gift.model.Wish;
import gift.repository.ProductDao;
import gift.repository.WishDao;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WishService {

    private final WishDao wishDao;
    private final ProductDao productDao;

    public WishService(WishDao wishDao, ProductDao productDao) {
        this.wishDao = wishDao;
        this.productDao = productDao;
    }

    public Wish makeWish(WishRequest request, String email) {
        productDao.find(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("해당 productId의 상품을 찾을 수 없습니다."));
        Wish wish = new Wish(request.getProductId(), email);
        wishDao.insert(wish);
        return wish;
    }
    public List<Product> getAllWishProductsByUser(String email) {
        return wishDao.findAll(email);
    }

    public void deleteWish(Long productId, String email) {
        if (getAllWishProductsByUser(email).isEmpty()) {
            throw new ProductNotFoundException("해당 productId의 상품이 위시리스트에 존재하지 않습니다.");
        }
        wishDao.delete(productId);
    }
}
