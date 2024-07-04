package gift.mapper;

import gift.DTO.ProductDTO;
import gift.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public ProductDTO createProduct(Long index, Product.CreateProduct create) {
    return new ProductDTO(index, create.getName(), create.getPrice(), create.getImageUrl());
  }

  public ProductDTO updateProduct(Long id, Product.UpdateProduct update) {
    return new ProductDTO(id,update.getName(),update.getPrice(), update.getImageUrl());
  }
}
