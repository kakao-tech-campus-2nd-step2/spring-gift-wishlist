package gift.converter;

import gift.domain.Product;
import gift.web.dto.request.CreateProductRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateRequestToProductConverter implements Converter<CreateProductRequest, Product> {

    @Override
    public Product convert(CreateProductRequest request) {
        return new Product.Builder()
            .name(request.getName())
            .price(request.getPrice())
            .imageUrl(request.getImageUrl())
            .build();
    }

}
