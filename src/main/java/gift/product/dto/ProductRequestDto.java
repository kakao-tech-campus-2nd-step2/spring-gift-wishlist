package gift.product.dto;

import gift.product.validation.ValidProductName;

import java.util.Objects;

// ValidProductName annotation 을 만들어서 이름값 검증을 수행하고 있다
// 이렇게 되면, Controller 에서 입력값 검증을 담당하게 되는 것인데
// 내부 다른 요청을 통해 Product 를 생성하는 경우에는 값 검증이 불가능하다
// 때문에, Controller 와 Domain 둘 다에 검증을 추가하는 것은 중복된 연산을 추가하는 것이라 좋지 않은 것 같고
// Domain 에서 검증을 하는 것이 좀 더 좋지 않을까?
public record ProductRequestDto(@ValidProductName String name, int price, String imageUrl) {
    public ProductRequestDto {
        Objects.requireNonNull(imageUrl);
    }

    public ServiceDto toServiceDto() {
        return new ServiceDto(null, this.name, this.price, this.imageUrl);
    }

    public ServiceDto toServiceDto(Long id) {
        return new ServiceDto(id, this.name, this.price, this.imageUrl);
    }
}
