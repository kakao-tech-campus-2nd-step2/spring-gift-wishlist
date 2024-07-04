package gift.service;

import gift.dao.ProductDAO;
import gift.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;

//    @Autowired  // 생성자가 하나밖에 없는 경우에는 어노테이션을 붙이지 않아도 의존성 주입 가능
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product getProductById(Long id) {
        return productDAO.findById(id);
    }

    public void addProduct(Product product) {
        productDAO.save(product);
    }

    public void updateProduct(Long id, Product product) {
        productDAO.update(id, product);
    }

    public void deleteProduct(Long id) {
        productDAO.delete(id);
    }
}
