package gift;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private static final String ADD_SUCCESS_MSG = "상품 추가 성공";
    private static final String UPDATE_SUCCESS_MSG = "상품 수정 성공";
    private static final String DELETE_SUCCESS_MSG = "상품 삭제 성공";

    private final Map<Long, Product> products = new HashMap<>();

    public Product getOneProduct(Long productId) {
        return products.get(productId);
    }

    public Collection<Product> getProduct() {
        return products.values();
    }

    public String addNewProduct(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
        return ADD_SUCCESS_MSG;
    }

    public String updateProductInfo(Long productId, Product product) {

        Product productToUpdate = products.get(productId);

        if (product.getName() != null) {
            productToUpdate.setName(product.getName());
        }
        if (product.getPrice() > 0) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getImageUrl() != null) {
            productToUpdate.setImageUrl(product.getImageUrl());
        }

        return UPDATE_SUCCESS_MSG;
    }

    public String deleteTheProduct(Long productId) {
        products.remove(productId);
        return DELETE_SUCCESS_MSG;
    }

}
