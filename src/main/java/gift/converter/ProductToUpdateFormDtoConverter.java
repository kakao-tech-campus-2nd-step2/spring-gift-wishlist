package gift.converter;

import gift.domain.Product;
import gift.web.dto.form.UpdateProductFormDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class ProductToUpdateFormDtoConverter implements Converter<Product, UpdateProductFormDto> {

    @Override
    public UpdateProductFormDto convert(@NonNull Product source) {
        return UpdateProductFormDto.from(source);
    }

}
