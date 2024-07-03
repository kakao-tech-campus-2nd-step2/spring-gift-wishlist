package gift.dto;

public class ProductResponseDTO {
    public final Long id;
    public final String name;
    public final int price;
    public final String imageUrl;

    public ProductResponseDTO(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}