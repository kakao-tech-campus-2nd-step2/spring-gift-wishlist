package gift.dto;

import gift.domain.Gift;

public record CreateGiftDto (String name, Integer price, String description, String url) {
    public Gift toGift() {
        return new Gift(name, description, price, url);
    }
}
