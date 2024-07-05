package gift.service;


import gift.model.Product;
import gift.dao.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public boolean addNewProduct(Product product){
        if (productDao.isProductInDB(product.id())) {
            return false;
        }
        productDao.insertProduct(product);
        return true;
    }

    public boolean updateProduct(Long id, Product product) {
        if (productDao.isProductInDB(id)) {
            productDao.updateProduct(product);
            return true;
        }
        return false;
    }

    public boolean purchaseProduct(Long id, int amount) {
        Product product = productDao.selectProduct(id);
        if (product.amount() >= amount) {
            productDao.purchaseProduct(id, amount);
            return true;
        }
        return false;
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
