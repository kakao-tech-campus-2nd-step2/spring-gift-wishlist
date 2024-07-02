package gift.form;

import gift.validation.NoKakao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class ProductAddForm {

    @NotBlank(message = "상품명을 입력해주세요.")
    @Length(min=1, max=15, message = "1~15자 사이로 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣0-9()+&/_ \\[\\]-]*$", message = "특수 문자는 '(, ), [, ], +, -, &, /, - 만 입력 가능합니다.'")
    @NoKakao
    private String name;

    private int price;

    private String imageUrl;

    public ProductAddForm() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductAddForm(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

}
