package gift.converter;

import gift.domain.Product;
import gift.web.dto.request.CreateProductRequest;
import org.springframework.stereotype.Component;

/**
 * 상품 관련 요청/응답 클래스를 상품 엔티티로 변환하는 컨버터 클래스입니다.
 */
@Component
public class ProductConverter {

    public Product convertToEntity(CreateProductRequest request) {
        return new Product.Builder()
            .name(request.getName())
            .price(request.getPrice())
            .imageUrl(request.getImageUrl())
            .build();
    }

}
