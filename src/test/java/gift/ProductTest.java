package gift;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.dto.ProductDTO;
import gift.model.Product;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    private Map<Long, Product> products;

    @BeforeEach
    void setUp() {
        products = new HashMap<>();
    }

    /**
     * 싱품 추가 테스트
     */
    @Test
    void postProductTest() {
        //given
        String name = "아이스 아메리카노 T";
        int price = 4500;
        String imageUrl = "testImageUrl.com";

        ProductDTO productDTO = new ProductDTO(name, price, imageUrl);
        String url = "http://localhost:"+port+"/products";

        //when
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, productDTO, Object.class);

        //then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Product product = objectMapper.convertValue(responseEntity.getBody(), Product.class);

        assertThat(product.getName()).isEqualTo("아이스 아메리카노 T");
        assertThat(product.getPrice()).isEqualTo(4500);
        assertThat(product.getImageUrl()).isEqualTo("testImageUrl.com");
    }

    @Test
    void getProductTest() {
        //given
        String name = "아이스 아메리카노 T";
        int price = 4500;
        String imageUrl = "testImageUrl.com";

        ProductDTO productDTO = new ProductDTO(name, price, imageUrl);
        String url = "http://localhost:"+port+"/products";
        ResponseEntity<Object> postResponseEntity = restTemplate.postForEntity(url, productDTO,
            Object.class);
        Product postProduct = objectMapper.convertValue(postResponseEntity.getBody(), Product.class);
        Long postId = postProduct.getId();
        System.out.println("postProduct = " + postProduct);

        //when
        url = "http://localhost:"+port+"/products/"+postId;
        ResponseEntity<Object> getResponseEntity = restTemplate.getForEntity(url, Object.class);
        Product getProduct = objectMapper.convertValue(getResponseEntity.getBody(), Product.class);
        Long getId = getProduct.getId();
        System.out.println("getProduct = " + getProduct);

        //then
        assertThat(getResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getProduct.getId()).isEqualTo(postId);
        assertThat(getProduct.getName()).isEqualTo(name);
        assertThat(getProduct.getPrice()).isEqualTo(price);
        assertThat(getProduct.getImageUrl()).isEqualTo(imageUrl);
    }

}
