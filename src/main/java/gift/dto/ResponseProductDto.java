package gift.dto;

import gift.domain.Product;

import java.util.List;

public record ResponseProductDto(
    Long id,
    String name,
    Integer price,
    String description,
    String imageUrl
) {

    public static ResponseProductDto from(Product product) {
        return new ResponseProductDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getDescription(),
            product.getUrl());
    }

    public static List<ResponseProductDto> of(List<Product> products) {
        return products.stream()
            .map(ResponseProductDto::from)
            .toList();
    }
}
