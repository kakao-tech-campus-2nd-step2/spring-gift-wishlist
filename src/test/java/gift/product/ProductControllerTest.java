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
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;
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
}
