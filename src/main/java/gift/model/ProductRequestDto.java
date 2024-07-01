package gift.model;

public record ProductRequestDto(String name, Long price, String imageUrl) {

    public static Product toEntity(Long id, ProductRequestDto requestDto) {
        return new Product(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }
}
