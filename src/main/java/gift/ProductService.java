package gift;

import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    public Product getProductById(long id) {
        return productDao.findById(id);
    }

    public void addProduct(Product newProduct){
        productDao.insertProduct(newProduct);
    }

    public void updateProduct(long id, Product updatedProduct){
        productDao.updateProduct(id, updatedProduct);
    }

    public void deleteProduct(long id){
        productDao.deleteById(id);
    }
}
