package gift.repository;

import gift.domain.Gift;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GiftCollectionRepository implements GiftRepository {
    private final Map<Long, Gift> gifts = new HashMap<>();

    @Override
    public Gift getGift(Long id) {
        var gift = gifts.get(id);
        return gift;
    }
}
