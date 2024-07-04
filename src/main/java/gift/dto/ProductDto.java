package gift.dto;

// dto를 record로 리팩토링
public record ProductDto(
    long id,
    String name,
    int price,
    String image) {
}
