package gift.dto;

import gift.domain.Gift;

public record GiftDto (String name, Integer price, String description, String imageUrl) {
    public static GiftDto from(Gift gift) {
        return new GiftDto(gift.getName(), gift.getPrice(), gift.getDescription(), gift.getUrl());
    }
}
