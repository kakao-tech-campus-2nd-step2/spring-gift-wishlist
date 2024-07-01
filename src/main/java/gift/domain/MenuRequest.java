package gift.domain;

public record MenuRequest(
        String name,
        int price,
        String imageUrl
) {
}
