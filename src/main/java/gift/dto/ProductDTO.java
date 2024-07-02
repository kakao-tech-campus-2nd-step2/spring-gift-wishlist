package gift.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        Integer id,
        @NotBlank(message = "상품 이름을 입력해주세요.") String name,
        @NotNull(message = "가격을 입력해주세요") @Min(value = 0, message = "가격은 0 이상이여야 합니다.") Integer price,
        @NotBlank(message = "이미지 URL을 입력해주세요.") String imageUrl
) {}
