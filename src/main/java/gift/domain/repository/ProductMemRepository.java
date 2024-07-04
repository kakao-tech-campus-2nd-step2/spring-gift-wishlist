package gift.domain.repository;

import gift.domain.model.Product;
import gift.domain.model.ProductDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ProductMemRepository {

    private final static Map<Long, Product> products = new HashMap<>();

    public boolean isExistProductId(Long id) {
        return products.containsKey(id);
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public boolean isEmptyProductList() {
        return products.isEmpty();
    }

    public List<ProductDto> getAllProduct() {
        return products.entrySet().stream()
            .map(entry -> new ProductDto(
                entry.getKey(),
                entry.getValue().getName(),
                entry.getValue().getPrice(),
                entry.getValue().getImageUrl()
            ))
            .collect(Collectors.toList());
    }

    public void addProduct(ProductDto productDto) {
        products.put(productDto.getId(), productDto.toProduct());
    }

    public void updateProduct(ProductDto productDto) {
        products.replace(productDto.getId(), productDto.toProduct());
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }
}
