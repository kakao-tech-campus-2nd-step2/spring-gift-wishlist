package gift.member;

import static org.assertj.core.api.Assertions.assertThat;

import gift.global.authentication.jwt.JwtToken;
import gift.global.authentication.jwt.JwtValidator;
import gift.global.authentication.jwt.TokenType;
import gift.member.persistence.repository.MemberRepository;
import gift.member.presentation.dto.RequestMemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtValidator jwtValidator;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testRegisterMember() {
        //given
        RequestMemberDto requestMemberDto = new RequestMemberDto(
            "test@example.com",
            "test");

        String url = "http://localhost:" + port + "/api/members/register";

        //when
        var response = restTemplate.postForEntity(url, requestMemberDto, JwtToken.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var jwtToken = response.getBody();
        var id = jwtValidator.validate("Bearer " + jwtToken.accessToken(), TokenType.ACCESS);
        var member = memberRepository.getMemberById(id);

        assertThat(member.getEmail()).isEqualTo(requestMemberDto.email());
    }

    @Test
    void testLoginMember() {
        //given
        RequestMemberDto requestMemberDto = new RequestMemberDto(
            "test@example.com",
            "test");

        String url = "http://localhost:" + port + "/api/members/login";

        //when
        var response = restTemplate.postForEntity(url, requestMemberDto, JwtToken.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        var jwtToken = response.getBody();
        var id = jwtValidator.validate("Bearer " + jwtToken.accessToken(), TokenType.ACCESS);
        var member = memberRepository.getMemberById(id);

        assertThat(member.getEmail()).isEqualTo(requestMemberDto.email());
    }
}
