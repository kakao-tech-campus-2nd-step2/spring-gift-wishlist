package gift.service;


import gift.model.Product;
import gift.dao.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public String purchaseProduct(Long id, int amount) {
        Product product = productDao.selectProduct(id);
        if (product.amount() >= amount) {
            productDao.purchaseProduct(id, amount);
            return "Purchase successful";
        }
        return "Not enough stock";
    }

    public Product selectProduct(Long id) {
        return productDao.selectProduct(id);
    }

    public List<Product> selectAllProducts(){
        return productDao.selectAllProducts();
    }

    public void DeleteProduct(Long id){
        productDao.deleteProduct(id);
    }
}
