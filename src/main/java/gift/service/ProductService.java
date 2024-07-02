package gift.service;

import gift.entity.Product;
import gift.exception.NoSuchProductIdException;
import gift.dao.ProductDao;
import gift.dto.ProductDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void addProduct(ProductDTO productDTO) throws RuntimeException {
        try {
            productDao.insertProduct(new Product(productDTO));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<ProductDTO> getProductList() {
        return ProductConverter.convertToProductDTO(productDao.selectProduct());
    }

    public void updateProduct(Integer id, ProductDTO productDTO) throws RuntimeException {
        ParameterValidator.validateParameter(id, productDTO);
        Product product = new Product(productDTO);

        if (productDao.updateProduct(product) == 0) {
            throw new NoSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
        }
    }

    public void deleteProduct(Integer id) throws RuntimeException {
        if(productDao.deleteProduct(id) == 0)
            throw new NoSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
    }
}
