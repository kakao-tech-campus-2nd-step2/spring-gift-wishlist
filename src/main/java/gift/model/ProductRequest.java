package gift.model;

public record ProductRequest(String name, int price, String imageUrl) {

    public Product toEntity(Long id) {
        return new Product(id, name, price, imageUrl);
    }
}
