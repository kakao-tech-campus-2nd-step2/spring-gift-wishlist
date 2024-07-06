package gift.service;

import gift.dto.wishlist.WishProductDto;
import gift.dto.wishlist.WishRequestDto;
import gift.entity.Wishlist;
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

    public List<WishProductDto> updateWishlist(Long userId, List<WishRequestDto> wishRequests) {
        for (WishRequestDto wishRequest : wishRequests) {
            Wishlist wishlist = WishlistMapper.toWishlist(userId, wishRequest);
            updateWish(wishlist);
        }
        return wishlistRepository.findByUserId(userId);
    }

    public void updateWish(Wishlist wishlist) {
        if (wishlist.productCount() == 0) {
            deleteWish(wishlist);
            return;
        }
        wishlistRepository.update(wishlist);
    }

    public List<WishProductDto> deleteWishlist(Long userId, List<WishRequestDto> wishRequests) {
        for (WishRequestDto wishRequest : wishRequests) {
            Wishlist wishlist = WishlistMapper.toWishlist(userId, wishRequest);
            deleteWish(wishlist);
        }
        return wishlistRepository.findByUserId(userId);
    }

    public void deleteWish(Wishlist wishlist) {
        wishlistRepository.delete(wishlist);
    }

}
