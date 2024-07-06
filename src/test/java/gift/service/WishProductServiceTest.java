package gift.service;

import gift.dto.WishProductAddRequest;
import gift.dto.WishProductUpdateRequest;
import gift.exception.ForeignKeyConstraintViolationException;
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

    @Test
    @DisplayName("관리자의 위시 리스트에 1번, 2번 상품을 추가하고, 이용자의 위시 리스트에 2번, 3번 상품을 추가하여 각각이 보는 위시 리스트가 다르다.")
    void addProduct1AndProduct2ToManagerAndAddProduct2AndProduct3ToMember() {
        var wishProduct1AddRequest = new WishProductAddRequest(product1Id, 5);
        var wishProduct2AddRequest = new WishProductAddRequest(product2Id, 5);
        var wishProduct3AddRequest = new WishProductAddRequest(product3Id, 5);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(0);
        Assertions.assertThat(wishProductService.getWishProducts(memberId).size()).isEqualTo(0);

        var managerWishProduct1 = wishProductService.addWishProduct(wishProduct1AddRequest, managerId);
        var managerWishProduct2 = wishProductService.addWishProduct(wishProduct2AddRequest, managerId);
        var memberWishProduct2 = wishProductService.addWishProduct(wishProduct2AddRequest, memberId);
        var memberWishProduct3 = wishProductService.addWishProduct(wishProduct3AddRequest, memberId);

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(2);
        Assertions.assertThat(wishProductService.getWishProducts(memberId).size()).isEqualTo(2);

        wishProductService.deleteWishProduct(managerWishProduct1.id());

        Assertions.assertThat(wishProductService.getWishProducts(managerId).size()).isEqualTo(1);
        Assertions.assertThat(wishProductService.getWishProducts(memberId).size()).isEqualTo(2);
        Assertions.assertThat(wishProductService.getWishProducts(memberId).get(0).product().id()).isEqualTo(product2Id);

        wishProductService.deleteWishProduct(managerWishProduct2.id());
        wishProductService.deleteWishProduct(memberWishProduct2.id());
        wishProductService.deleteWishProduct(memberWishProduct3.id());
    }

    @Test
    @DisplayName("존재하지 않는 상품 ID 로 위시 리스트 상품 추가 요청시 예외 발생")
    void addWishProductFailWithInvalidProductId() {
        var invalidWishProductAddRequest = new WishProductAddRequest(10L, 5);

        Assertions.assertThatThrownBy(() -> wishProductService.addWishProduct(invalidWishProductAddRequest, memberId))
                .isInstanceOf(ForeignKeyConstraintViolationException.class);
    }

    @Test
    @DisplayName("이미 위시 리스트 상품에 존재하는 상품을 추가 요청시 갯수 증가")
    void addWishProductAlreadyExistProduct() {
        var wishProduct1AddRequest = new WishProductAddRequest(product1Id, 5);

        Assertions.assertThat(wishProductService.getWishProducts(memberId).size()).isEqualTo(0);

        wishProductService.addWishProduct(wishProduct1AddRequest, memberId);

        Assertions.assertThat(wishProductService.getWishProducts(memberId).size()).isEqualTo(1);
        Assertions.assertThat(wishProductService.getWishProducts(memberId).get(0).count()).isEqualTo(5);

        var wishProduct = wishProductService.addWishProduct(wishProduct1AddRequest, memberId);

        Assertions.assertThat(wishProductService.getWishProducts(memberId).size()).isEqualTo(1);
        Assertions.assertThat(wishProductService.getWishProducts(memberId).get(0).count()).isEqualTo(10);

        wishProductService.deleteWishProduct(wishProduct.id());
    }
}
