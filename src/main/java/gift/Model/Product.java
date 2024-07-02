package gift.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Product(Long id,
                      @NotBlank(message = "상품 이름은 최소 1자 이상이어야 합니다.")
                      @Size(max = 15, message = "상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다.")
                      String name, long price, String imageUrl) {

}
