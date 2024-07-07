package gift.controller;

import gift.dto.WishListDTO;
import gift.model.LoginToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WishListControllerTest {

    @LocalServerPort
    private int port;
    private String baseUrl;
    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port;
        webClient = WebTestClient.bindToServer().baseUrl(baseUrl).build();
    }

    @Test
    @DisplayName("위시 리스트 추가")
    void addWishList() {
        //given
        LoginToken loginToken = registerAndLogin("test", "123");

        //when
        addWishListPutRequest(loginToken);

        //then

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

    private LoginToken registerAndLogin(String email, String password) {
        return null;
    }

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
}