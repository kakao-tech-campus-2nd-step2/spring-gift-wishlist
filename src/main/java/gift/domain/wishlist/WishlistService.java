package gift.domain.wishlist;

import gift.domain.product.ProductRepository;
import gift.domain.product.exception.ProductNotFoundException;
import gift.domain.user.User;
import gift.domain.wishlist.dto.WishlistAddResponseDto;
import gift.domain.wishlist.dto.WishlistDeleteRequestDto;
import gift.domain.wishlist.dto.WishlistDto;
import gift.domain.wishlist.exception.WishlistProductNotIncludedException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    private void checkProductExists(Long productId) {
        //존재하지 않는 상품이면 예외 발생
        productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

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
        checkProductExists(wishlistDto.productId());
        return wishlistRepository.save(WishlistDto.toEntity(wishlistDto, user));
    }

    public WishlistDto updateWishlist(User user, WishlistDto wishlistDto) {
        checkProductExists(wishlistDto.productId());
        wishlistRepository.findByUserEmailAndProductId(user.email(), wishlistDto.productId())
            .orElseThrow(WishlistProductNotIncludedException::new);
        wishlistRepository.update(WishlistDto.toEntity(wishlistDto, user));
        return wishlistDto;
    }

    public void deleteWishlist(User user, WishlistDeleteRequestDto deleteRequestDto) {
        checkProductExists(deleteRequestDto.productId());
        wishlistRepository.delete(WishlistDeleteRequestDto.toEntity(deleteRequestDto, user));
    }
}
