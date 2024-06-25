package gift.service;

import gift.domain.Gift;
import gift.dto.CreateGiftDto;
import gift.dto.GiftDto;
import gift.repository.GiftRepository;
import org.springframework.stereotype.Service;

@Service
public class GiftService {
    private final GiftRepository giftRepository;

    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }


    public GiftDto getGift(Long id) {
        Gift gift = giftRepository.getGift(id);
        return GiftDto.from(gift);
    }

    public Long createGift(CreateGiftDto giftDto) {
        Gift gift = giftDto.toGift();
        return giftRepository.saveGift(gift);
    }
}
