package gift.domain.service;

import gift.domain.dto.WishlistResponseDto;
import gift.domain.entity.Wishlist;
import gift.domain.repository.ProductRepository;
import gift.domain.exception.ProductNotFoundException;
import gift.domain.entity.User;
import gift.domain.dto.WishlistAddResponseDto;
import gift.domain.dto.WishlistDeleteRequestDto;
import gift.domain.dto.WishlistRequestDto;
import gift.domain.exception.WishlistProductNotIncludedException;
import gift.domain.repository.WishlistRepository;
import java.util.List;
import java.util.Optional;
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

    public List<WishlistResponseDto> getWishlist(User user) {
        return wishlistRepository.findWishlistByUser(user);
    }

    public WishlistAddResponseDto addWishlist(User user, WishlistRequestDto wishlistRequestDto) {
        checkProductExists(wishlistRequestDto.productId());
        Optional<Wishlist> search = wishlistRepository.findByUserEmailAndProductId(user.email(), wishlistRequestDto.productId());

        //아이템이 없고 수량이 1 이상일 때 새 데이터 삽입
        if (search.isEmpty()) {
            if (wishlistRequestDto.quantity() <= 0) {
                // 0 이하인 경우 아무 작업 하지 않음
                return new WishlistAddResponseDto("nope", 0L);
            }
            wishlistRepository.save(WishlistRequestDto.toEntity(wishlistRequestDto, user));
            return new WishlistAddResponseDto("create", wishlistRequestDto.quantity());
        }

        //수량은 최소한 0 이상이어야 함
        WishlistRequestDto newWishlistRequestDto = new WishlistRequestDto(wishlistRequestDto.productId(),
            wishlistRequestDto.quantity() + search.get().quantity());

        //업데이트 후 수량이 음수면 delete 수행
        if (newWishlistRequestDto.quantity() <= 0) {
            wishlistRepository.delete(WishlistRequestDto.toEntity(newWishlistRequestDto, user));
            return new WishlistAddResponseDto("delete", 0L);
        }

        // 아이템이 이미 존재하므로 업데이트 수행
        wishlistRepository.update(WishlistRequestDto.toEntity(newWishlistRequestDto, user));
        return new WishlistAddResponseDto("add", newWishlistRequestDto.quantity());
    }

    public WishlistResponseDto updateWishlist(User user, WishlistRequestDto wishlistRequestDto) {
        checkProductExists(wishlistRequestDto.productId());
        wishlistRepository.findByUserEmailAndProductId(user.email(), wishlistRequestDto.productId())
            .orElseThrow(WishlistProductNotIncludedException::new);
        wishlistRepository.update(WishlistRequestDto.toEntity(wishlistRequestDto, user));
        return WishlistResponseDto.of(wishlistRequestDto.quantity(), productRepository.findById(wishlistRequestDto.productId()).get());
    }

    public void deleteWishlist(User user, WishlistDeleteRequestDto deleteRequestDto) {
        checkProductExists(deleteRequestDto.productId());
        wishlistRepository.delete(WishlistDeleteRequestDto.toEntity(deleteRequestDto, user));
    }
}
