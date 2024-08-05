package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    @Autowired
    private WishRepository wishRepository;

    @Autowired
    private ProductService productService;

    public List<Wish> getWishesByMemberId(Long memberId) {
        return wishRepository.findByMemberId(memberId);
    }

    public void addWish(Long memberId, Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        Wish wish = new Wish(memberId, productId);
        wishRepository.save(wish);
    }

    public void deleteWish(Long memberId, Long productId) {
        wishRepository.deleteByMemberIdAndProductId(memberId, productId);
    }
}
