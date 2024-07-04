package gift.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class ProductDTO {

    @Size(max = 15, message = "상품 이름은 최대 15자까지 입력할 수 있습니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9 ()\\[\\]+,&/_-]*$",
            message = "상품 이름에는 영문자, 숫자, 공백, (), [], +, -, &, /, _ 만 사용할 수 있습니다."
    )
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.contains("카카오")) {
            throw new IllegalArgumentException("상품 이름에 '카카오'가 포함된 경우 담당 MD와 협의가 필요합니다.");
        }
        this.name = name;
    }
}
