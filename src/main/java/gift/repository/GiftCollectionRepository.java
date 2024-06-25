package gift.repository;

import gift.domain.Gift;
import gift.exception.ErrorCode;
import gift.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class GiftCollectionRepository implements GiftRepository {
    private final Map<Long, Gift> gifts = new HashMap<>();
    private final AtomicLong currentId = new AtomicLong(0L);

    @Override
    public Gift getGift(Long id) {
        var gift = gifts.get(id);
        if (gift == null) {
            throw new NotFoundException(ErrorCode.GIFT_NOT_FOUND);
        }
        return gift;
    }

    @Override
    public Long saveGift(Gift gift) {
        Long id = currentId.getAndIncrement();
        gifts.put(id, gift);
        return id;
    }

    @Override
    public Long updateGift(Long id, Gift gift) {
        if (!gifts.containsKey(id)) {
            throw new NotFoundException(ErrorCode.GIFT_NOT_FOUND);
        }
        gifts.put(id, gift);
        return id;
    }

    @Override
    public void deleteGiftById(Long id) {
        if (gifts.remove(id) == null) {
            throw new NotFoundException(ErrorCode.GIFT_NOT_FOUND);
        }
    }
}
