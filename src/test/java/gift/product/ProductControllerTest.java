package gift.product;


import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.RequestEntity;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
class ProductControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    //private ProductDao productDao;
    private TestRestTemplate restTemplate;

    @Test
    void port() {
        assertThat(port).isNotZero();
    }

    @Test
    void create() {
        var url = "http://localhost:" + port + "/api/products";
        var request = new ProductRequestDto("사과",2000,"www");
        var requestEntity = new RequestEntity<>(request, POST, URI.create(url));
        var actual = restTemplate.exchange(requestEntity, String.class);
        assertThat(actual.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void readAll() {
        var url = "http://localhost:" + port + "/api/products";
        var actual = restTemplate.getForEntity(url,String.class);
        assertThat(actual.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void read() {
        // given
        var createUrl = "http://localhost:" + port + "/api/products";
        var request = new ProductRequestDto("사과", 2000, "www");
        var requestEntity = new RequestEntity<>(request, POST, URI.create(createUrl));
        var createResponse = restTemplate.exchange(requestEntity, String.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(OK);

        //when
        Long productId = 1L;

        var readUrl = "http://localhost:" + port + "/api/products/" + productId;
        var response = restTemplate.getForEntity(readUrl, String.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void delete() {
        // given
        var createUrl = "http://localhost:" + port + "/api/products";
        var request = new ProductRequestDto("사과", 2000, "www");
        var requestEntity = new RequestEntity<>(request, POST, URI.create(createUrl));
        var createResponse = restTemplate.exchange(requestEntity, String.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(OK);

        //when
        Long productId = 1L;

        var deleteUrl = "http://localhost:" + port + "/api/products/" + productId;
        restTemplate.delete(deleteUrl);
        var response = restTemplate.getForEntity(deleteUrl, String.class);

        assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);

    }

    @Test
    void update() {
        // given
        var createUrl = "http://localhost:" + port + "/api/products";
        var request = new ProductRequestDto("사과", 2000, "www");
        var requestEntity = new RequestEntity<>(request, POST, URI.create(createUrl));
        var createResponse = restTemplate.exchange(requestEntity, String.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(OK);

        // when
        Long productId = 1L;

        var updateUrl = "http://localhost:" + port + "/api/products/" + productId;
        var update = new ProductRequestDto("파김치", 10000, "www.com");
        var requestUpdate = new RequestEntity<>(update, PUT, URI.create(updateUrl));
        var updateResponse = restTemplate.exchange(requestUpdate, String.class);
        assertThat(updateResponse.getStatusCode()).isEqualTo(OK);
    }
}
