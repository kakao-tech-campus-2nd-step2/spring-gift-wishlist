package gift.product.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.lang.Nullable;

public class PostProductReq {
    @NotNull
    @Size(max = 15, message = "상품 이름은 15자 이하로 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9 ()\\[\\]\\+\\-&/_]*$", message = "사용할 수 없는 특수문자가 포함되어 있습니다.")
    private String name;
    @NotNull
    @Min(value = 0, message = "상품 가격은 0 이상의 정수로 입력해주세요.")
    private int price = 0;
    @Nullable
    private String imageUrl;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setPrice(@NotNull @Min(0) int price) {
        this.price = price;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
