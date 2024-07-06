package gift.service;

import gift.dto.WishProductAddRequest;
import gift.dto.WishProductUpdateRequest;
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

    @Test
    @DisplayName("관리자의 위시 리스트에 1번 상품, 2번 상품을 추가한 후에 2번 상품 삭제하기")
    void addProduct1AndProduct2ToManagerAndRemoveWishProduct2() {
        var wishProduct1AddRequest = new WishProductAddRequest(product1Id, 5);
        var wishProduct2AddRequest = new WishProductAddRequest(product2Id, 5);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(0);

        var wishProduct1 = wishProductService.addWishProduct(wishProduct1AddRequest, managerId);
        var wishProduct2 = wishProductService.addWishProduct(wishProduct2AddRequest, managerId);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(2);

        wishProductService.deleteWishProduct(wishProduct2.id());

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(1);

        wishProductService.deleteWishProduct(wishProduct1.id());
    }

    @Test
    @DisplayName("관리자의 위시 리스트에 1번 상품, 2번 상품을 추가한 후에 2번 상품 수량 0으로 변경하기")
    void addProduct1AndProduct2ToManagerAndUpdateWishProduct2WithZeroCount() {
        var wishProduct1AddRequest = new WishProductAddRequest(product1Id, 5);
        var wishProduct2AddRequest = new WishProductAddRequest(product2Id, 5);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(0);

        var wishProduct1 = wishProductService.addWishProduct(wishProduct1AddRequest, managerId);
        var wishProduct2 = wishProductService.addWishProduct(wishProduct2AddRequest, managerId);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(2);

        var wishProduct2UpdateRequest = new WishProductUpdateRequest(0);

        wishProductService.updateWishProduct(wishProduct2.id(), wishProduct2UpdateRequest);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(1);

        wishProductService.deleteWishProduct(wishProduct1.id());
    }
}
