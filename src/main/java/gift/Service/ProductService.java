package gift.Service;

import gift.Model.ProductDAO;
import gift.Model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductModel> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public ProductModel getProductById(Long id) {
        return productDAO.getProductById(id);
    }

    public void saveProduct(ProductModel product) {
        productDAO.saveProduct(product);
    }

    public void updateProduct(ProductModel product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }
}
