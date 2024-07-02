package gift.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Product(Long id,
                      @NotBlank(message = "상품 이름은 최소 1자 이상이어야 합니다.")
                      @Size(max = 15, message = "상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다.")
                      @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$", message = "상품 이름에 (), [], +, -, &, /, _ 외 특수 문자는 사용할 수 없습니다.")
                      String name, long price, String imageUrl) {

}
