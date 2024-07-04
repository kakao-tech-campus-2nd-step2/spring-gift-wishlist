package gift.service;

import gift.dao.ProductDao;
import gift.vo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getProducts();
    }

    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

    public Boolean addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public Boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    public Boolean deleteProduct(Long id) {
        return productDao.deleteProduct(id);
    }
}
