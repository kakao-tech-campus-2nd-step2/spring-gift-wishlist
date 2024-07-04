package gift.product.model.dto;

import gift.product.model.dto.valid.ValidProductName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

public class CreateProductRequest {
    @ValidProductName
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
