package gift.repository;

import gift.domain.Wish;
import gift.dto.WishRequestDto;
import java.util.List;
import java.util.Optional;

public interface WishRepository {

    void addWish(Wish wish);

}
