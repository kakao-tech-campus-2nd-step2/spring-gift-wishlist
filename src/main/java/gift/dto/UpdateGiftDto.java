package gift.dto;

import gift.domain.Gift;

public record UpdateGiftDto (Long id, String name, Integer price, String description, String imageUrl) {
    public Gift toGift() {
        return new Gift(name, description, price, imageUrl);
    }
}
