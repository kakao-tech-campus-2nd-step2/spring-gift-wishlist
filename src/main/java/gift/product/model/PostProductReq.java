package gift.product.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

public class PostProductReq {
    @NotNull
    private String name;
    @NotNull
    @Min(0)
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
}
