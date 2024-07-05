package gift.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

// dto를 record로 리팩토링
@Validated
public record ProductDto(
    @Min(value = 1, message = "id는 1 이상이어야 합니다.")
    @NotNull(message = "id는 필수로 입력해야 합니다.")
    long id,
    @Size(max = 15, message = "상품명은 최대 15자입니다.")
    @NotBlank(message = "상품명은 공백일 수 없습니다.")
    @Pattern(regexp = "^[\\(\\)\\[\\]\\+\\-\\&\\/\\_\\p{Alnum}\\s\\uAC00-\\uD7A3]+$", message = "상품명에 ( ), [ ], +, -, &, /, _를 제외한 특수문자를 사용할 수 없습니다.")
    String name,
    @Min(value = 0, message = "가격은 음수일 수 없습니다.")
    @NotNull(message = "가격은 필수로 입력해야 합니다.")
    int price,
    @NotBlank(message = "잘못된 이미지입니다.")
    String image,
    boolean md) {

    public ProductDto {
        verifyKakao(name, md);
    }

    // 카카오라는 이름을 쓴 경우에 대한 검증
    private void verifyKakao(String name, boolean md) {
        if (!md && name.contains("카카오")) {
            throw new IllegalArgumentException("카카오라는 이름은 MD와 사전 협의 후 사용 가능합니다.");
        }
    }

    // md의 t/f 여부에 따라 Yes/No로 반환하는 메서드. view를 위해 사용
    public String mdToString() {
        if (md) {
            return "Yes";
        }

        return "No";
    }
}
