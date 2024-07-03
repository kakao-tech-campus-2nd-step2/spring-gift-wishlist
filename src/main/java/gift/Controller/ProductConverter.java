package gift.Controller;

import gift.Model.Product;
import gift.Model.ProductDTO;

public class ProductConverter {
    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.id(), product.name(), product.price(), product.imageUrl());
    }

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());
    }

}
