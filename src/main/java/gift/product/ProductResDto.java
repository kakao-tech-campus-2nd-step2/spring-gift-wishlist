package gift.product;

public record ProductResDto(
        Long id,
        String name,
        Integer price,
        String imageUrl
) {
}
