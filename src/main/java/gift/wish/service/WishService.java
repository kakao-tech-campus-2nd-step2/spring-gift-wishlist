package gift.wish.service;

import gift.wish.domain.Wish;
import gift.wish.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<Wish> getAllWishesByMemberId(Long memberId) {
        return wishRepository.findAll(memberId);
    }
}
