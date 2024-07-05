package gift.repository;

import gift.Dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;


@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 안 되면 DataSoruce
    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean isExistEmail(String email) {
        String sql = "select * from users where email = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> new User(
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("type")
        ));
        if (users.size() > 0) return true;
        return false;
    }

    @Override
    public Optional<User> save(User user) {
        String email = user.getEmail();
        if(!isExistEmail(email)) {
            String sql = "insert into users (email, password, type) values (?, ?, ?)";
            jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getType());s
            return Optional.of(user);
        }
        return Optional.empty();
    }


}
