package gift.model.product;

public record Product(long id, ProductName name, int price, String imageUrl, int amount) { }
