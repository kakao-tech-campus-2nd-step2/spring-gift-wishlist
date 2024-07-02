package gift;

import gift.exception.ProductAlreadyExistsException;
import gift.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product makeProduct(ProductRequestDto requestDto) {
        Product getProduct = productDao.find(requestDto.getId());

        if (getProduct == null) {
            Product product = new Product(
                    requestDto.getId(),
                    requestDto.getName(),
                    requestDto.getPrice(),
                    requestDto.getImageUrl()
            );
            productDao.insert(product);
            return product;
        }
        throw new ProductAlreadyExistsException("이미 해당 id의 상품이 존재합니다.");
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProduct(Long id) {
        Product product = productDao.find(id);
        if (product == null) {
            throw new ProductNotFoundException("해당 id의 상품이 존재하지 않습니다.");
        }
        return product;
    }

    public Product putProduct(ProductRequestDto requestDto) {
        Product getProduct = productDao.find(requestDto.getId());

        if (getProduct != null) {
            Product updateProduct = new Product(
                    requestDto.getId(),
                    requestDto.getName(),
                    requestDto.getPrice(),
                    requestDto.getImageUrl()
            );
            productDao.update(requestDto.getId(), updateProduct);
            return updateProduct;
        }
        throw new ProductNotFoundException("수정하려는 해당 id의 상품이 존재하지 않습니다.");
    }

    public void deleteProduct(Long id) {
        Product product = productDao.find(id);
        if (product == null) {
            throw new ProductNotFoundException("삭제하려는 해당 id의 상품이 존재하지 않습니디.");
        }
        productDao.delete(id);
    }
}
