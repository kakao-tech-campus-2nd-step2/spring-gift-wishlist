package gift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

public record ProductRequest(
        @Pattern(regexp = "^[\s\\-\\&\\(\\)\\[\\]\\+\\/\\_a-zA-z0-9ㄱ-ㅎ가-힣]*$", message = "허용되지 않은 형식의 이름입니다.")
        @Pattern(regexp = "(?!.*카카오).*", message = "카카오가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.")
        @Length(max = 15, message = "이름의 길이는 15자를 초과할 수 없습니다.")
        @Length(min = 1, message = "이름의 길이는 최소 1자 이상이어야 합니다.")
        String name,
        @PositiveOrZero(message = "금액은 0보다 크거나 같아야 합니다.")
        Integer price,
        @NotBlank
        String imageUrl) {
}
