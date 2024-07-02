package gift.service;

import gift.dto.ProductDTO;
import gift.exception.InvalidIdException;
import java.util.Objects;

public class ParameterValidator {
    public static void validateParameter(Integer id, ProductDTO productDTO) throws InvalidIdException {
        if (!Objects.equals(productDTO.id(), id)) {
            throw new InvalidIdException("올바르지 않은 id입니다.");
        }
    }
}
