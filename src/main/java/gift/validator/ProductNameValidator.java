package gift.validator;

import gift.model.Product;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductNameValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        String name = product.getName();
        if (name == null || name.length() > 15) {
            errors.rejectValue("name", "name.size", "상품의 이름은 최대 15자까지 입력할 수 있습니다.");
        }

    }
}
