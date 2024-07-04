package gift.product.model.dto;

public record GetProductRes(
        Long id,
        String name,
        int price,
        String imageUrl) {
}