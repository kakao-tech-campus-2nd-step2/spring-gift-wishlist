package gift.service;

import gift.entity.Product;
import gift.entity.ProductDao;
import gift.entity.ProductName;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product addProduct(Product product) {
        Product newProduct = new Product(null, product.name, product.price, product.imageUrl);
        Long productId = productDao.insertProduct(newProduct);
        return new Product(productId, product.name, product.price, product.imageUrl);
    }

    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProductOptional = productDao.selectProduct(id);
        Product existingProduct = existingProductOptional.orElseThrow(() ->
                new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + id, HttpStatus.NOT_FOUND));

        Product updatedProduct = existingProduct.update(product.name, product.price, product.imageUrl);
        productDao.updateProduct(updatedProduct);
        return updatedProduct;
    }

    public List<Product> getAllProducts() {
        return productDao.selectAllProducts();
    }

    public boolean deleteProduct(Long id) {
        productDao.selectProduct(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + id, HttpStatus.NOT_FOUND));
        productDao.deleteProduct(id);
        return true;
    }
}
