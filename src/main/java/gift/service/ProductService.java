package gift.service;
import gift.dao.ProductDao;
import gift.entity.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private ProductDao productDao;

  @Autowired
  public ProductService(ProductDao productDao) {
    this.productDao = productDao;
  }

  public List<Product> findAll(){
    return productDao.findAll();
  }

  public Product getProductById(long id) {
    return productDao.findById(id);
  }

  public Product addProduct(Product product) {
    productDao.save(product);
    return product;
  }
  public Product updateProduct(Product product) {
    productDao.update(product);
    return product;
  }

  public void deleteProduct(long id) {
    productDao.deleteById(id);
  }

}

