package gift.repository;

import gift.domain.Wish;

import java.util.List;
import java.util.Optional;

public interface WishRepository {
    void save(Wish wish);
    Optional<List<Wish>> findByUserId(Long userId);
    Optional<Wish> findById(Long id);
    void delete(Long id);
    void updateQuantity(Long id, int quantity);
}
