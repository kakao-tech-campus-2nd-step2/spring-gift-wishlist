package gift.repository.impls;

import gift.domain.User;
import gift.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user){
        var sql = "INSERT INTO users(email, password) VALUES (?,?)";
        jdbcTemplate.update(sql,user.getEmail(),user.getPassword());
    }
    @Override
    public Optional<User> findByPasswordAndEmail(String email, String password) {
        var sql = "SELECT * FROM users WHERE email = ? And password = ?";
        try {
            User user = jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNum) -> new User(
                            resultSet.getLong("id"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    )
                    ,email
                    ,password
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
