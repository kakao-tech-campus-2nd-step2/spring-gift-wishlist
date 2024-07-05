package gift.service;

import gift.domain.model.WishResponseDto;
import gift.domain.repository.WishRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<WishResponseDto> getWishesByUserEmail(String email) {
        return wishRepository.getWishesByUserEmail(email);
    }
}
