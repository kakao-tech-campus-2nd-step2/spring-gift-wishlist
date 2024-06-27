package gift.converter;

import gift.domain.Product;
import gift.web.dto.request.CreateProductRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateRequestToProductConverter {

    public Product convertToEntity(CreateProductRequest request) {
        return new Product.Builder()
            .name(request.getName())
            .price(request.getPrice())
            .imageUrl(request.getImageUrl())
            .build();
    }

}
