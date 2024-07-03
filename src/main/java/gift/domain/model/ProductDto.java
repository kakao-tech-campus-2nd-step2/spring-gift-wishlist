package gift.domain.model;

public class ProductDto {

    Long id;
    String name;
    Long price;
    String imageUrl;

    public ProductDto(Long id, String name, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product toProduct() {
        return new Product(name, price, imageUrl);
    }

}
