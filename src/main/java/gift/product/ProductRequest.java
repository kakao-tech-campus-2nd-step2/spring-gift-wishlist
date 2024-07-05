package gift.product;

public record ProductRequest(
    String name,
    long price,
    String imageUrl
) {

    public Product toProduct(long id) {
        return new Product(id, name, price, imageUrl);
    }
}
