package gift.validation.wishlist;

import gift.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductNotFoundValidator implements ConstraintValidator<ProductNotFound, Long> {

    private final ProductRepository productRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return productRepository.findById(value).isPresent();
    }

}
