package gift.service;

import gift.domain.Product;
import gift.domain.ProductDaoImpl;
import gift.exception.DuplicateProductNameException;
import gift.domain.ProductValidator;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDaoImpl productDao;


    private final ProductValidator productValidator = new ProductValidator();


    public ProductService(ProductDaoImpl productDao) {
        this.productDao = productDao;
        productDao.createProductTable();
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProductById(Long id) {
        return productDao.findById(id);
    }

    public void saveProduct(Product product) {
        //DB 저장 시 이미 중복된 ID가 있는지 검사.
        if (!productDao.productNameCheck(product.getName())) {
            throw new DuplicateProductNameException(
                "Product Name " + product.getName() + " already exists.");
        }

        productDao.save(product);
    }

    public void updateProduct(Product product, Long id) {
        productDao.update(product, id);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

}
