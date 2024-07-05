package gift.service;

import gift.model.Product;
import gift.repository.JdbcProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final JdbcProductRepository productRepository;

  public ProductService(JdbcProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Optional<Product> findById(Long id) {
    return Optional.ofNullable(productRepository.findById(id));
  }

  public Product save(Product product) {
    if (product.getId() == null) {
      productRepository.save(product);
    } else {
      productRepository.update(product);
    }
    return product;
  }

  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }
}
