package gift.product.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.lang.Nullable;

public class UpdateProductRequest {
    @NotNull
    private Long id;
    @ValidProductName
    private String name;
    @NotNull
    @Min(value = 0, message = "상품 가격은 0 이상의 정수로 입력해주세요.")
    private int price = 0;
    @Nullable
    private String imageUrl;

    public UpdateProductRequest(Long id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.price = price;
        this.name = name.isBlank() ? null : name;
        this.imageUrl = imageUrl.isBlank() ? null : imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}