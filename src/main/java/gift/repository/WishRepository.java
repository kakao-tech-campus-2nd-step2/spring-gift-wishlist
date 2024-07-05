package gift.repository;

import gift.domain.Wish;

import java.util.List;

public interface WishRepository {
    void save(Wish wish);
    List<Wish> findByUserId(Long userId);
    void deleteByUserIdAndProductId(Long userId, Long productId);
    void updateQuantity(Long userId, Long productId, int quantity);
}
