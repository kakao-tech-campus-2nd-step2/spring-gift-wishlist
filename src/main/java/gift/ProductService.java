package gift;

import gift.exception.ProductNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static gift.Message.*;
import static gift.exception.ErrorCode.*;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getOneProduct(Long productId) {
        return productRepository.selectOneProduct(productId);
    }

    public List<Product> getProduct() {
        return productRepository.selectAllProducts();
    }

    public String addProduct(Product newProduct) {

        String productName = newProduct.getName();

        if (!ProductNameValidationUtil.isValidLength(productName))
            throw new ProductNameException(LENGTH_ERROR);
        if (ProductNameValidationUtil.containsSpecialCharacters(productName))
            throw new ProductNameException(SPECIAL_CHAR_ERROR);
        if (ProductNameValidationUtil.containsKAKAO(productName))
            throw new ProductNameException(KAKAO_CONTAIN_ERROR);

        productRepository.insertProduct(newProduct);
        return ADD_SUCCESS_MSG;
    }

    public String updateProduct(Long productId, Product product) {

        Product productToUpdate = productRepository.selectOneProduct(productId);

        if (product.getName() != null) {
            productToUpdate.setName(product.getName());
        }
        if (product.getPrice() > 0) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getImageUrl() != null) {
            productToUpdate.setImageUrl(product.getImageUrl());
        }
        productRepository.updateProduct(productToUpdate);
        return UPDATE_SUCCESS_MSG;
    }

    public String deleteProduct(Long productId) {
        productRepository.deleteProduct(productId);
        return DELETE_SUCCESS_MSG;
    }

}
