package gift.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record WishListAddRequest(
    @NotNull(message = "회원 id를 입력해주세요.")
    @JsonProperty("member_id")
    Long memberId,
    @NotNull(message = "상품 id를 입력해주세요.")
    @JsonProperty("product_id")
    Long productId,
    @NotNull(message = "수량을 입력해주세요.")
    @Range(min = 1, max = 999, message = "위시 리스트에 상품은 1~999개까지 담을 수 있습니다.")
    Integer quantity
) {

}
