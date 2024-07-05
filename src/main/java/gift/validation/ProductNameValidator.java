package gift.validation;

import gift.exception.NotValidProductNameException;
import gift.model.Product;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, Product> {

    private static final String VALID_PATTERN = "^[A-Za-z0-9 ()\\[\\]+\\-&/_ㄱ-ㅣ가-힣]+$";

    @Override
    public void initialize(ValidProductName constraintAnnotation) {
    }
    @Override
    public boolean isValid(Product product, ConstraintValidatorContext context) {
        String value = product.getName();

        if(value.length()>15){
            throw new NotValidProductNameException("이름은 공백 포함 최대 15자입니다.", product);
        }
        if (value.contains("카카오")) {
            throw new NotValidProductNameException("'카카오'가 포함된 문구는 담당 MD와 협의한 경우만 가능합니다.", product);
        }
        if(!value.matches(VALID_PATTERN)){
            throw new NotValidProductNameException("특수문자는 (),[],+,-,&,/,_만 사용가능합니다.", product);
        }
        return true;
    }
}