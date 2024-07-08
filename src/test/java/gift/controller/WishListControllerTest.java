package gift.controller;

import gift.service.AuthenticationTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WishListControllerTest {

    @LocalServerPort
    private int port;
    WebTestClientHelper webClient;

    @BeforeEach
    void setUp() {
        webClient = new WebTestClientHelper(port);
    }

    @Test
    @DisplayName("WebTestClientHelper 정상 작동 확인")
    void isOk() {

        var response = webClient.get("/admin/products");

        response.expectStatus().isOk();
        response.expectHeader().contentType("application/json");
        //response.expectBody().json(...)

    }


    @Test
    @DisplayName("위시 리스트 조회")
    void getWishList() {

    }

    @Test
    @DisplayName("위시 리스트 아이템 수량 변경")
    void updateWishList() {

    }

    @Test
    @DisplayName("위시 리스트 아이템 삭제")
    void deleteWishList() {

    }

    private AuthenticationTool registerAndLogin(String email, String password) {
        return null;
    }
    /*
    private void addWishListPutRequest(long memberId, WishListDTO wishListDTO) {
        ResponseSpec responseSpec = webClient.put().uri(uriBuilder -> {
                return uriBuilder
                    .path("/api/member")
                    .queryParam("memberId", loginToken.getMemberId())
                    .path("/wishlist")
                    .build();
            }).accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(wishListDTO)).exchange();
    }

     */
}