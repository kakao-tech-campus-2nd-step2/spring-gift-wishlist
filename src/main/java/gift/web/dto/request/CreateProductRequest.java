package gift.web.dto.request;

import java.net.URL;

public class CreateProductRequest {

    private String name;
    private Integer price;
    private URL imageUrl;

    public CreateProductRequest(String name, Integer price, URL imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public URL getImageUrl() {
        return imageUrl;
    }
}
