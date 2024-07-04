package gift.entity;

public class Product {
    public final Long id;
    public final ProductName name;
    public final int price;
    public final String imageUrl;

    public Product(Long id, ProductName name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product update(ProductName name, int price, String imageUrl) {
        return new Product(this.id, name, price, imageUrl);
    }
}
