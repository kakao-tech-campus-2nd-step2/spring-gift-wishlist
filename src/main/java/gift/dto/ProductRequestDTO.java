package gift.dto;

public class ProductRequestDTO {
    public final String name;
    public final int price;
    public final String imageUrl;

    public ProductRequestDTO(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}