package gift.prodcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
        productDAO.create();
        productDAO.insert(new Product(1, "test", 1, "test"));
    }

    public List<Product> getAllProducts() {
        return productDAO.selectAll();
    }

    public Product getProductById(long id) {
        return productDAO.select(id);
    }

    public void addProduct(Product product) {
        productDAO.insert(product);
    }

    public void updateProduct(long id, Product product) {
        productDAO.update(id, product);
    }

    public void deleteProduct(long id) {
        productDAO.delete(id);
    }
}
