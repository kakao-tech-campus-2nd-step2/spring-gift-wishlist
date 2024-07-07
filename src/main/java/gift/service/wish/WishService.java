package gift.service.wish;

import gift.domain.wish.WishRepository;
import gift.web.dto.WishDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<WishDto> findAllWishes(String email) {
        return List.copyOf(wishRepository.selectAllWishes(email)
                            .stream()
                            .map(WishDto::from)
                            .toList()
                            );
    }

}
