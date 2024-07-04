package gift.product.validation;

import gift.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidation {
    private final ProductService productService;

    @Autowired
    public ProductValidation(ProductService productService) {
        this.productService = productService;
    }

    public boolean existsById(Long id) {
        return productService.existsById(id);
    }

}
