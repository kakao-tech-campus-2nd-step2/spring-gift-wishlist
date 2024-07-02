package gift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public record ProductRequest(
        @NotBlank(message = "상품 이름은 공백일 수 없습니다.")
        @Size(max = 15, message = "상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있습니다.")
        @Pattern(regexp = "^[a-zA-Z0-9 ()\\[\\]+\\-&/_]*$", message = "상품 이름이 유효하지 않은 문자를 포함하고 있습니다.")
        String name,

        int price,

        String imageUrl) {
    public ProductRequest {
        Objects.requireNonNull(name, "상품 이름은 공백일 수 없습니다.");
        Objects.requireNonNull(imageUrl, "이미지 URL은 공백일 수 없습니다.");
    }
}
