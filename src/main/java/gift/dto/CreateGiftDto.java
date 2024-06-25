package gift.dto;

import gift.domain.Gift;
import gift.exception.ErrorCode;
import gift.exception.InvalidRequestException;

public record CreateGiftDto (String name, Integer price, String description, String imageUrl) {
    public Gift toGift() {
        return new Gift(name, description, price, imageUrl);
    }

    public CreateGiftDto {
        if (name == null || name.isBlank()) {
            throw new InvalidRequestException(ErrorCode.INVALID_REQUEST);
        }
        if (price == null || price <= 0) {
            throw new InvalidRequestException(ErrorCode.INVALID_REQUEST);
        }
        if (description == null || description.isBlank()) {
            throw new InvalidRequestException(ErrorCode.INVALID_REQUEST);
        }
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new InvalidRequestException(ErrorCode.INVALID_REQUEST);
        }
    }
}
