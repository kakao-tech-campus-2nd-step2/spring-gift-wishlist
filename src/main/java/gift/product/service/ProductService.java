package gift.product.service;

import gift.product.dao.ProductDao;
import gift.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
        productDao.createProductTable();
    }

    public void registerProduct(Product product) {
        productDao.registerProduct(product);
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }

    public Collection<Product> getAllProducts() {
        return productDao.listupProducts();
    }

    public Collection<Product> searchProducts(String keyword) {
        return productDao.searchProduct(keyword);
    }

    public boolean existsById(Long id) {
        return productDao.existsById(id);
    }
}
