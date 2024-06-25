package gift.product.model;

import jakarta.validation.constraints.Min;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class PostProductReq {
    @NonNull
    private String name;
    @NonNull
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
