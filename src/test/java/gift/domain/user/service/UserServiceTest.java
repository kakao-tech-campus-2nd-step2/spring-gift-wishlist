package gift.domain.user.service;

import static org.assertj.core.api.Assertions.*;

import gift.auth.jwt.Token;
import gift.domain.user.dao.UserDao;
import gift.domain.user.dto.UserDto;
import gift.domain.user.dto.UserLoginDto;
import gift.domain.user.entity.Role;
import gift.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    void signUp() {
        // given
        UserDto userDto = new UserDto(null, "testUser", "test123@test.com", "test123", null);

        // when
        Token token = userService.signUp(userDto);

        // then
        assertThat(token).isNotNull();
    }

    @Test
    void login() {
        // given
        userDao.insert(new User(null, "testUser", "test456@test.com", "test123", Role.USER));
        UserLoginDto loginDto = new UserLoginDto("test456@test.com", "test123");

        // when
        Token token = userService.login(loginDto);

        // then
        assertThat(token).isNotNull();
    }
}