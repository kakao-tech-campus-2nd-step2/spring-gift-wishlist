package gift.util;

import gift.dto.ProductDTO;
import gift.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getImageURL());
    }

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());
    }
}
