package gift;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import gift.dao.UserDAO;
import gift.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
@Import(UserDAO.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSaveAndFindByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        userDAO.save(user);

        User foundUser = userDAO.findByEmail("test@example.com");
        assertNotNull(foundUser);
    }
}
