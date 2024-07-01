package gift.product.restapi.dto.request;

public record ProductUpdateRequest(
        String name,
        Integer price,
        String imageUrl
) {
}
