package gift.Service;

import gift.DTO.ProductDto;
import gift.Repository.ProductDao;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductService {
  private final ProductDao productDao;
  public ProductService(ProductDao productDao){
    this.productDao=productDao;
  }

  public List<ProductDto> getAllProducts() {
    List<ProductDto> ProductDtos = productDao.selectAllProducts();
    return ProductDtos;
  }

  public ProductDto getProductById(Long id) {
    return productDao.selectProduct(id);

  }

  public ProductDto addProduct(@Valid ProductDto productDTO){
    productDao.insertProduct(productDTO);
    return productDTO;
  }
  
  public ProductDto updateProduct(Long id, @Valid ProductDto updatedProductDto) {
    ProductDto existingProductDto = productDao.selectProduct(id);
    if (existingProductDto !=null){
      updatedProductDto.setId(id);
      productDao.updateProduct(updatedProductDto);
    }
    return existingProductDto;
  }

  public ProductDto deleteProduct(@PathVariable Long id) {
    ProductDto existingProductDto = productDao.selectProduct(id);
    if (existingProductDto != null) {
      productDao.deleteProduct(id);
    }
    return existingProductDto;
  }

}