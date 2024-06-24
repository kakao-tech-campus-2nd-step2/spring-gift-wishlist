package gift.service;

import gift.domain.Gift;
import gift.repository.GiftRepository;
import org.springframework.stereotype.Service;

@Service
public class GiftService {
    private final GiftRepository giftRepository;

    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }


    public Gift getGift(Long id) {
        return giftRepository.getGift(id);
    }
}
