package gift.service;


import gift.model.Product;
import gift.dao.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public String addNewProduct(Product product){
        if (productDao.checkProduct(product.id())) {
            return "Already exists id";
        }
        productDao.insertProduct(product);
        return "Add successful";
    }

    public String purchaseProduct(Long id, int amount) {
        Product product = productDao.selectProduct(id);
        if (product.amount() >= amount) {
            productDao.purchaseProduct(id, amount);
            return "Purchase successful";
        }
        return "Not enough stock";
    }
}
