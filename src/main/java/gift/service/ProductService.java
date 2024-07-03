package gift.service;

import gift.entity.Product;
import gift.entity.ProductDao;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product addProduct(Product product) {
        if (product.name.contains("카카오")) {
            throw new BusinessException(ErrorCode.KAKAO_NAME_NOT_ALLOWED, HttpStatus.BAD_REQUEST);
        }
        Long productId = productDao.insertProduct(product);
        return new Product(productId, product.name, product.price, product.imageUrl);
    }

    public Product updateProduct(Long id, Product product) {
        if (product.name.contains("카카오")) {
            throw new BusinessException(ErrorCode.KAKAO_NAME_NOT_ALLOWED, HttpStatus.BAD_REQUEST);
        }
        Product existingProduct = productDao.selectProduct(id);
        if (existingProduct == null) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + id, HttpStatus.NOT_FOUND);
        }
        Product updatedProduct = new Product(id, product.name, product.price, product.imageUrl);
        productDao.updateProduct(updatedProduct);
        return updatedProduct;
    }

    public List<Product> getAllProducts() {
        return productDao.selectAllProducts();
    }

    public boolean deleteProduct(Long id) {
        Product product = productDao.selectProduct(id);
        if (product == null) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + id, HttpStatus.NOT_FOUND);
        }
        productDao.deleteProduct(id);
        return true;
    }
}
