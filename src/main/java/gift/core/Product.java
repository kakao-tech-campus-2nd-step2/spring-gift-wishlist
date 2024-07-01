package gift.core;

public record Product(
        Long id,
        String name,
        Integer price,
        String imageUrl
) {
}
