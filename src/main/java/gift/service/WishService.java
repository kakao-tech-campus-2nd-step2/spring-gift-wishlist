package gift.service;

import gift.model.Wish;
import gift.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<Wish> getWishesByMemberId(Long memberId) {
        return wishRepository.findByMemberId(memberId);
    }

    public Wish addWish(Wish wish) {
        return wishRepository.save(wish);
    }

    public void deleteWish(Long memberId, Long productId) {
        wishRepository.deleteByMemberIdAndProductId(memberId, productId);
    }
}
