package gift.service;

import gift.dto.wishlist.WishDto;
import gift.dto.wishlist.WishRequestDto;
import gift.entity.Wish;
import gift.exception.wish.WishNotFoundException;
import gift.mapper.WishMapper;
import gift.repository.ProductRepository;
import gift.repository.WishRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepository wishRepository;
    private final ProductRepository productRepository;

    public WishService(WishRepository wishRepository, ProductRepository productRepository) {
        this.wishRepository = wishRepository;
        this.productRepository = productRepository;
    }

    public List<WishDto> getWishes(Long userId) {
        if (userId == null) {
            throw new WishNotFoundException("해당 사용자의 위시 리스트가 존재하지 않습니다.");
        }

        return wishRepository.findByUserId(userId);
    }

    public List<WishDto> addWish(Long userId, WishRequestDto wishRequest) {
        return wishRepository.insert(
            WishMapper.toWish(userId, wishRequest)
        );
    }

    public List<WishDto> updateWishes(Long userId, List<WishRequestDto> wishRequests) {
        for (WishRequestDto wishRequest : wishRequests) {
            Wish wish = WishMapper.toWish(userId, wishRequest);
            updateWish(wish);
        }
        return wishRepository.findByUserId(userId);
    }

    public void updateWish(Wish wish) {
        if (wish.quantity() <= 0) {
            deleteWish(wish);
            return;
        }
        wishRepository.update(wish);
    }

    public List<WishDto> deleteWishes(Long userId, List<WishRequestDto> wishRequests) {
        for (WishRequestDto wishRequest : wishRequests) {
            Wish wish = WishMapper.toWish(userId, wishRequest);
            deleteWish(wish);
        }
        return wishRepository.findByUserId(userId);
    }

    public void deleteWish(Wish wish) {
        wishRepository.delete(wish);
    }

}
