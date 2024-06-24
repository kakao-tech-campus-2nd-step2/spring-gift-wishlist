package gift.repository;

import gift.domain.Gift;
import gift.exception.ErrorCode;
import gift.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GiftCollectionRepository implements GiftRepository {
    private final Map<Long, Gift> gifts = new HashMap<>();

    @Override
    public Gift getGift(Long id) {
        var gift = gifts.get(id);
        if (gift == null) {
            throw new NotFoundException(ErrorCode.GIFT_NOT_FOUND);
        }
        return gift;
    }
}
