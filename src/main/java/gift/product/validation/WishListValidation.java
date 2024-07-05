package gift.product.validation;

import gift.product.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishListValidation {
    private final AdminProductService adminProductService;

    @Autowired
    public WishListValidation(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    public boolean isExistsProduct(Long id) {
        return adminProductService.existsById(id);
    }

}
