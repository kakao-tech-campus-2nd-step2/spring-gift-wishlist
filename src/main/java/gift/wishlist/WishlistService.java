package gift.wishlist;

import gift.member.Member;
import gift.product.Product;
import gift.product.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    public WishlistService(
        WishlistRepository wishlistRepository,
        ProductRepository productRepository
    ) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getAllWishlists(Member member) {
        return wishlistRepository.getAllWishlists(member);
    }

    public void addWishlist(Member member, long productId) {
        if (wishlistRepository.existWishlist(new Wishlist(productId, member.email()))) {
            throw new IllegalArgumentException("Wishlist already exists");
        }
        hasProductByProductID(productId);
        wishlistRepository.addWishlist(new Wishlist(productId, member.email()));
    }

    public void deleteWishlist(Member member, long productId) {
        hasProductByProductID(productId);
        wishlistRepository.deleteWishlist(new Wishlist(productId, member.email()));
    }

    private void hasProductByProductID(long productId) {
        if (!productRepository.existProduct(productId)) {
            throw new IllegalArgumentException("Product does not exist");
        }
    }
}
