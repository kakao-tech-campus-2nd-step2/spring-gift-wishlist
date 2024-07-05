package gift.service;

import gift.dto.wishlist.WishProductDto;
import gift.dto.wishlist.WishRequestDto;
import gift.mapper.WishlistMapper;
import gift.repository.WishlistRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<WishProductDto> getWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public List<WishProductDto> addWishlist(Long userId, WishRequestDto wishRequest) {
        return wishlistRepository.insert(
            WishlistMapper.toWishlist(userId, wishRequest)
        );
    }

}
