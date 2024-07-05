package gift.service;

import gift.domain.Wish;
import org.springframework.stereotype.Service;
import gift.repository.WishRepository;

import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void addWish(Long memberId, Long productId) {
        Wish wish = new Wish(memberId, productId);
        wishRepository.save(wish);
    }

    public List<Wish> getWishes(Long memberId) {
        return wishRepository.findByMemberId(memberId);
    }

    public void removeWish(Long memberId, Long productId) {
        wishRepository.deleteByMemberIdAndProductId(memberId, productId);
    }
}
