package gift.domain.wishlist;

import gift.domain.product.ProductRepository;
import gift.domain.product.exception.ProductNotFoundException;
import gift.domain.user.User;
import gift.domain.wishlist.dto.WishlistAddResponseDto;
import gift.domain.wishlist.dto.WishlistDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }

    public List<WishlistDto> getWishlist(User user) {
        return wishlistRepository.findWishlistByUser(user).stream()
            .map(WishlistDto::of)
            .toList();
    }

    public WishlistAddResponseDto addWishlist(User user, WishlistDto wishlistDto) {
        //존재하지 않는 상품이면 예외 발생
        productRepository.findById(wishlistDto.productId()).orElseThrow(ProductNotFoundException::new);

        return wishlistRepository.save(
            new Wishlist(wishlistDto.productId(), user.email(), wishlistDto.quantity()));
    }
}
