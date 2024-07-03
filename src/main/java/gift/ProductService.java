package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final String ADD_SUCCESS_MSG = "상품 추가 성공";
    private static final String UPDATE_SUCCESS_MSG = "상품 수정 성공";
    private static final String DELETE_SUCCESS_MSG = "상품 삭제 성공";

    @Autowired
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getOneProduct(Long productId) {
        return productDao.selectOneProduct(productId);
    }

    public List<Product> getAllProduct() {
        return productDao.selectAllProducts();
    }

    public String addProduct(Product newProduct) {
        productDao.insertProduct(newProduct);
        return ADD_SUCCESS_MSG;
    }

    public String updateProduct(Long productId, Product product) {

        Product productToUpdate = productDao.selectOneProduct(productId);

        if (product.getName() != null) {
            productToUpdate.setName(product.getName());
        }
        if (product.getPrice() > 0) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getImageUrl() != null) {
            productToUpdate.setImageUrl(product.getImageUrl());
        }
        productDao.updateProduct(productToUpdate);
        return UPDATE_SUCCESS_MSG;
    }

    public String deleteProduct(Long productId) {
        productDao.deleteProduct(productId);
        return DELETE_SUCCESS_MSG;
    }

}
