package gift.product.model;

public record GetProductRes(
        Long id,
        String name,
        int price,
        String imageUrl) {
}