package gift.service;

import gift.model.Wish;
import gift.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;

    @Autowired
    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<Wish> getWishesByMemberId(Long memberId) {
        return wishRepository.findByMemberId(memberId);
    }

    public Wish addWish(Wish wish) {
        return wishRepository.save(wish);
    }

    @Transactional
    public void deleteWish(Long memberId, Long id) {
        wishRepository.deleteByMemberIdAndId(memberId, id);
    }
}
