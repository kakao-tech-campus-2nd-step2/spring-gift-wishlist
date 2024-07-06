package gift.service;

import gift.dto.WishProductAddRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WishProductServiceTest {

    @Autowired
    private WishProductService wishProductService;

    private final Long managerId = 1L;
    private final Long memberId = 2L;

    private final Long product1Id = 1L;
    private final Long product2Id = 2L;
    private final Long product3Id = 3L;

    @Test
    @DisplayName("관리자의 위시 리스트에 1번 상품을 추가")
    void addProduct1ToManager() {
        var wishProductAddRequest = new WishProductAddRequest(product1Id, 5);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(0);

        var wishProduct = wishProductService.addWishProduct(wishProductAddRequest, managerId);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(1);

        wishProductService.deleteWishProduct(wishProduct.id());

    }
}
