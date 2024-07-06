package gift.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.dto.LoginRequest;
import gift.dto.WishProductAddRequest;
import gift.dto.WishProductResponse;
import gift.dto.WishProductUpdateRequest;
import gift.service.MemberService;
import gift.service.WishProductService;
import gift.utils.AuthUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WishProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WishProductService wishProductService;
    @Autowired
    private MemberService memberService;
    @Value("${SECRET_KEY}")
    private String secretKey;
    private String managerToken;
    private String memberToken;

    @BeforeEach
    @DisplayName("관리자, 이용자의 토큰 값 세팅하기")
    void setAccessToken() {
        managerToken = memberService.login(new LoginRequest("admin@naver.com", "password")).token();
        memberToken = memberService.login(new LoginRequest("member@naver.com", "password")).token();
    }

    @Test
    @DisplayName("잘못된 수량으로 된 위시 리스트 상품 추가하기")
    void addWishProductFailWithZeroCount() throws Exception {
        var result = mockMvc.perform(post("/api/wishes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken)
                .content(objectMapper.writeValueAsString(new WishProductAddRequest(1L, 0))));

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("상품의 수량은 반드시 1개 이상이어야 합니다."));
    }

    @Test
    @DisplayName("위시 리스트 상품 추가하기")
    void addWishProductSuccess() throws Exception {
        var result = mockMvc.perform(post("/api/wishes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken)
                .content(objectMapper.writeValueAsString(new WishProductAddRequest(1L, 10))));

        result.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("위시 리스트 상품 추가후 조회한 다음 삭제하기")
    void addWishProductAndDeleteWishProductSuccess() throws Exception {
        var wishProduct = wishProductService
                .addWishProduct(new WishProductAddRequest(1L, 10),
                        AuthUtils.getMemberIdWithToken(memberToken, secretKey));

        var readResult = mockMvc.perform(get("/api/wishes")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken));
        var wishResult = readResult.andExpect(status().isOk()).andReturn();
        var wishResponseContent = wishResult.getResponse().getContentAsString();
        var wishProducts = objectMapper.readValue(wishResponseContent, new TypeReference<List<WishProductResponse>>() {
        });

        Assertions.assertThat(wishProducts.size()).isEqualTo(1);

        wishProductService.deleteWishProduct(wishProduct.id());

        var deleteResult = mockMvc.perform(delete("/api/wishes/"+wishProduct.id())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken));
        var wishDeleteResult = deleteResult.andExpect(status().isNoContent()).andReturn();
        var wishDeleteResponseContent = wishDeleteResult.getResponse().getContentAsString();

        Assertions.assertThat(wishDeleteResponseContent).isEmpty();
    }

    @Test
    @DisplayName("이미 위시 리스트에 저장된 상품 추가로 저장시 수량이 늘어난다")
    void addWishProductAlreadyExistWishProductSuccess() throws Exception {
        var wishProductAddRequest = new WishProductAddRequest(1L, 10);
        var wishProduct = wishProductService.addWishProduct(wishProductAddRequest, AuthUtils.getMemberIdWithToken(memberToken, secretKey));

        mockMvc.perform(post("/api/wishes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken)
                .content(objectMapper.writeValueAsString(wishProductAddRequest)));
        var addResult = mockMvc.perform(post("/api/wishes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken)
                .content(objectMapper.writeValueAsString(wishProductAddRequest)));

        addResult.andExpect(status().isCreated());

        var wishProducts = wishProductService.getWishProducts(AuthUtils.getMemberIdWithToken(memberToken, secretKey));

        Assertions.assertThat(wishProducts.size()).isEqualTo(1);
        Assertions.assertThat(wishProducts.get(0).count()).isEqualTo(30);

        wishProductService.deleteWishProduct(wishProduct.id());
    }

    @Test
    @DisplayName("관리자와 이용자의 위시리스트가 다르다")
    void addWishProductAndReadMemberAndManagerSuccess() throws Exception {
        var wishProduct1AddRequest = new WishProductAddRequest(1L, 10);
        var wishProduct2AddRequest = new WishProductAddRequest(2L, 10);
        var wishProduct3AddRequest = new WishProductAddRequest(3L, 10);

        wishProductService.addWishProduct(wishProduct1AddRequest, AuthUtils.getMemberIdWithToken(memberToken, secretKey));
        wishProductService.addWishProduct(wishProduct2AddRequest, AuthUtils.getMemberIdWithToken(memberToken, secretKey));
        wishProductService.addWishProduct(wishProduct2AddRequest, AuthUtils.getMemberIdWithToken(managerToken, secretKey));
        wishProductService.addWishProduct(wishProduct3AddRequest, AuthUtils.getMemberIdWithToken(managerToken, secretKey));

        var memberReadResult = mockMvc.perform(get("/api/wishes")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken));
        var memberWishResult = memberReadResult.andExpect(status().isOk()).andReturn();
        var memberWishResponseContent = memberWishResult.getResponse().getContentAsString();
        var memberWishProducts = objectMapper.readValue(memberWishResponseContent, new TypeReference<List<WishProductResponse>>() {
        });
        var memberWishProductsWithIds = memberWishProducts.stream().map(WishProductResponse::id).toList();
        var memberWishProductsWithProductIds = memberWishProducts.stream().map(wishProductResponse -> wishProductResponse.product().id()).toList();

        Assertions.assertThat(memberWishProducts.size()).isEqualTo(2);
        Assertions.assertThat(memberWishProductsWithProductIds).contains(1L, 2L);

        var managerReadResult = mockMvc.perform(get("/api/wishes")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + managerToken));
        var managerWishResult = managerReadResult.andExpect(status().isOk()).andReturn();
        var managerWishResponseContent = managerWishResult.getResponse().getContentAsString();
        var managerWishProducts = objectMapper.readValue(managerWishResponseContent, new TypeReference<List<WishProductResponse>>() {
        });
        var managerWishProductsWithIds = managerWishProducts.stream().map(WishProductResponse::id).toList();
        var managerWishProductsWithProductIds = managerWishProducts.stream().map(wishProductResponse -> wishProductResponse.product().id()).toList();

        Assertions.assertThat(managerWishProducts.size()).isEqualTo(2);
        Assertions.assertThat(managerWishProductsWithProductIds).contains(2L, 3L);

        for (var id : memberWishProductsWithIds) {
            wishProductService.deleteWishProduct(id);
        }

        for (var id : managerWishProductsWithIds) {
            wishProductService.deleteWishProduct(id);
        }
    }

    @Test
    @DisplayName("위시 리스트 상품 추가후 수량 변경하기")
    void addWishProductAndUpdateCountSuccess() throws Exception {
        var wishProduct = wishProductService
                .addWishProduct(new WishProductAddRequest(1L, 10),
                        AuthUtils.getMemberIdWithToken(memberToken, secretKey));

        mockMvc.perform(put("/api/wishes/update/" + wishProduct.id())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken)
                .content(objectMapper.writeValueAsString(new WishProductUpdateRequest(30))));

        var wishProducts = wishProductService.getWishProducts(AuthUtils.getMemberIdWithToken(memberToken, secretKey));

        Assertions.assertThat(wishProducts.size()).isEqualTo(1);
        Assertions.assertThat(wishProducts.get(0).count()).isEqualTo(30);

        wishProductService.deleteWishProduct(wishProduct.id());
    }

    @Test
    @DisplayName("위시 리스트 상품 추가후 수량 0으로 변경하기")
    void addWishProductAndUpdateCountZeroSuccess() throws Exception {
        var wishProduct = wishProductService
                .addWishProduct(new WishProductAddRequest(1L, 10),
                        AuthUtils.getMemberIdWithToken(memberToken, secretKey));

        mockMvc.perform(put("/api/wishes/update/" + wishProduct.id())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken)
                .content(objectMapper.writeValueAsString(new WishProductUpdateRequest(0))));

        var wishProducts = wishProductService.getWishProducts(AuthUtils.getMemberIdWithToken(memberToken, secretKey));

        Assertions.assertThat(wishProducts.size()).isEqualTo(0);
    }
}
