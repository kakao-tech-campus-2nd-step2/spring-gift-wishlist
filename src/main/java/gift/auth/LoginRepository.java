package gift.auth;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {

    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isExist(Login login) {
        String sql = "SELECT EXISTS(SELECT 1 FROM Users WHERE email = ? and password = ? and isDelete=0)";
        if (jdbcTemplate.queryForObject(sql, new Object[]{login.getEmail(), login.getPassword()}, Integer.class) == 1) {
            return true;
        }
        return false;
    }
}
