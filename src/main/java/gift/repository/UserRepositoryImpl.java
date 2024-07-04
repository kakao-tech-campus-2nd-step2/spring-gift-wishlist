package gift.repository;

import gift.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void save(User user) {
        String sql = "INSERT INTO users(password,email) VALUES (?,?)";
        jdbcTemplate.update(sql,user.getPassword(),user.getEmail());
    }

}
