package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public boolean updateProduct(Long id, Product product) {
    if (!productRepository.findById(id).isPresent()) {
      return false;
    }
    product.setId(id);
    productRepository.save(product);
    return true;
  }

  public boolean deleteById(Long id) {
    if (!productRepository.findById(id).isPresent()) {
      return false;
    }
    productRepository.deleteById(id);
    return true;
  }
}
