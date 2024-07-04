package gift.service;

import gift.Product;
import gift.dao.ProductDao;
import gift.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void save(ProductModel productModel) {
        Product product = new Product(
                productModel.getName(),
                productModel.getPrice(),
                productModel.getImgUrl()
        );
        productDao.save(product);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(Long id) {
        return productDao.findById(id);
    }

    public void update(Long id, ProductModel productModel) {
        Product product = new Product(
                id,
                productModel.getName(),
                productModel.getPrice(),
                productModel.getImgUrl()
        );
        productDao.update(product);
    }

    public void deleteById(Long id) {
        productDao.deleteById(id);
    }
}