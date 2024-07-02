package gift.service;

import gift.entity.Product;
import gift.entity.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
        this.productDao.createProductTable();
    }

    public Product addProduct(Map<String, Object> productData) {
        String name = (String) productData.get("name");
        int price = (Integer) productData.get("price");
        String imageUrl = (String) productData.get("imageUrl");
        Product product = new Product(null, name, price, imageUrl);
        Long productId = productDao.insertProduct(product);
        return new Product(productId, name, price, imageUrl);
    }

    public List<Product> getAllProducts() {
        return productDao.selectAllProducts();
    }

    public Product updateProduct(Long id, Map<String, Object> productData) {
        String name = (String) productData.get("name");
        int price = (Integer) productData.get("price");
        String imageUrl = (String) productData.get("imageUrl");
        Product product = productDao.selectProduct(id);
        if (product == null) {
            return null;
        }
        product = new Product(id, name, price, imageUrl);
        productDao.updateProduct(product);
        return product;
    }

    public boolean deleteProduct(Long id) {
        Product product = productDao.selectProduct(id);
        if (product == null) {
            return false;
        }
        productDao.deleteProduct(id);
        return true;
    }
}
