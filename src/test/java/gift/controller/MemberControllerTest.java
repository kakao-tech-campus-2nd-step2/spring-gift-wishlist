package gift.controller;

import gift.dto.MemberDTO;
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
class MemberControllerTest {

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
    @DisplayName("회원가입 성공")
    void registerMember() {
        //given
        String email = "abcd@gmail.com";
        String password = "abcd";
        MemberDTO dto = new MemberDTO(email, password, null);

        //when
        ResponseSpec responseSpec = registerMemberPutRequest(dto);

        //then
        responseSpec.expectStatus().isOk();
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 시도")
    void registerDuplicateEmail() {
        //given
        String email = "abcd@gmail.com";

        MemberDTO dto = new MemberDTO(email, "1234", null);
        MemberDTO dto2 = new MemberDTO(email, "4567", null);

        //when
        ResponseSpec responseSpec = registerMemberPutRequest(dto);
        ResponseSpec responseSpec2 = registerMemberPutRequest(dto2);

        responseSpec2.expectStatus().isForbidden();
    }

    @Test
    @DisplayName("이메일을 입력하지 않은 경우")
    void nullEmail() {
        //given
        MemberDTO dto = new MemberDTO(null, "1234", null);

        //when
        ResponseSpec responseSpec = registerMemberPutRequest(dto);

        //then
        responseSpec.expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("패스워드를 입력하지 않은 경우")
    void nullPassword() {
        //given
        MemberDTO dto = new MemberDTO("abcd@abcd", null, null);

        //when
        ResponseSpec responseSpec = registerMemberPutRequest(dto);

        //then
        responseSpec.expectStatus().isBadRequest();
    }


    @Test
    @DisplayName("로그인 성공")
    void login() {
        //given
        String email = "abcd@gmail.com";
        String password = "abcd";
        MemberDTO dto = new MemberDTO(email, password, null);
        ResponseSpec responseSpec = registerMemberPutRequest(dto);

        //when
        ResponseSpec responseSeec = webClient.get().uri(uriBuilder -> uriBuilder
            .path("/api/member/login")
            .queryParam("email", dto.getEmail())
            .queryParam("password", dto.getPassword())
            .build()).accept(MediaType.APPLICATION_JSON).exchange();

        //then
        responseSeec.expectStatus().isOk();
        responseSeec.expectBody(LoginToken.class);
    }
    /*
    @Test
    @DisplayName("패스워드가 불일치한 경우")

    @Test
    @DisplayName("회원가입이 되지않은 이메일로 로그인을 시도하는 경우")

    @Test
    @DisplayName("로그인 성공으로 Token을 받아오기 성공")
    */

    private ResponseSpec registerMemberPutRequest(MemberDTO dto) {
        ResponseSpec responseSpec = webClient.put().uri("/api/member")
            .accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(dto)).exchange();
        return responseSpec;
    }

}