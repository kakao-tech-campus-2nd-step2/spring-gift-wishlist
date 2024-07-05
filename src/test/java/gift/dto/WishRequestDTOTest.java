package gift.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gift.dto.wish.WishRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WishRequestDTOTest {

    @Test
    @DisplayName("WishRequestDTO 생성 테스트")
    public void testCreateWishRequestDTO() {
        Long memberId = 1L;
        Long productId = 1L;
        WishRequestDTO wishRequestDTO = new WishRequestDTO(memberId, productId);

        assertThat(wishRequestDTO.memberId()).isEqualTo(memberId);
        assertThat(wishRequestDTO.productId()).isEqualTo(productId);
    }

    @Test
    @DisplayName("WishRequestDTO 필드 값 테스트")
    public void testWishRequestDTOFields() {
        Long memberId = 2L;
        Long productId = 2L;
        WishRequestDTO wishRequestDTO = new WishRequestDTO(memberId, productId);

        assertThat(wishRequestDTO.memberId()).isEqualTo(memberId);
        assertThat(wishRequestDTO.productId()).isEqualTo(productId);
    }
}
