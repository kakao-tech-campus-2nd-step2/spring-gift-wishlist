package gift.product.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

public class PatchProductReq {
    @NotNull
    private Long id;
    @Nullable
    private String name;
    @Nullable
    private int price = 0;
    @Nullable
    private String imageUrl;

    public PatchProductReq(Long id, String name, Integer price, String imageUrl) {
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