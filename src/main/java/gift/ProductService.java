package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<ProductDTO> getAllProducts() {
        return productDao.findAll();
    }

    public ProductDTO getProductById(Long id) {
        return productDao.selectProduct(id);
    }

    public void addProduct(ProductDTO product) {
        productDao.insertProduct(product);
    }

    public void updateProduct(ProductDTO product) {
        productDao.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}
