package gift.repository;

import gift.domain.Gift;

public interface GiftRepository {
    Gift getGift(Long id);

    Long saveGift(Gift gift);

    Long updateGift(Long id, Gift gift);
}
