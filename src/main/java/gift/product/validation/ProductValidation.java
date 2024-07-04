package gift.product.validation;

import gift.product.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidation {
    private final AdminProductService adminProductService;

    @Autowired
    public ProductValidation(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    public boolean existsById(Long id) {
        return adminProductService.existsById(id);
    }

}
