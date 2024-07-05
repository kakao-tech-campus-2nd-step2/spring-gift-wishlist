package gift.service;

import gift.DTO.Product;
import gift.repository.ProductRepository;
import gift.repository.WishlistRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    @Autowired
    public WishlistService(WishlistRepository wr, ProductRepository pr) {
        wishlistRepository = wr;
        productRepository = pr;
    }

    public List<Product> getWishlistByEmail(String email) {
        // 사용자 이메일을 기반으로 Wishlist에서 productId 리스트를 가져온 다음
        // ProductRepository를 검색하여 상품 목록 리턴
        return null;
    }
}
