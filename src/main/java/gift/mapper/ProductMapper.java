package gift.mapper;

import gift.dto.ProductRequestDTO;
import gift.dto.ProductResponseDTO;
import gift.entity.Product;
import gift.entity.ProductName;

public class ProductMapper {

    public static Product toProduct(ProductRequestDTO productRequestDTO) {
        ProductName productName = new ProductName(productRequestDTO.name);
        return new Product(null, productName, productRequestDTO.price, productRequestDTO.imageUrl);
    }

    public static ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(product.id, product.name.getValue(), product.price, product.imageUrl);
    }
}
