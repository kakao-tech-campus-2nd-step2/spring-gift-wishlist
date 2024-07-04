package gift.repository;

import gift.domain.ProductEntity;
import gift.domain.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private Long id = 0L;
    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    /*
     * User 정보를 받아 DB에 저장
     */
    public void save(UserEntity user){
        var sql = "INSERT INTO users(id, email, password) VALUES (?,?,?)";
        jdbcTemplate.update(sql, id++, user.getEmail(), user.getPassword());
    }
    /*
     * User 정보를 email을 기준으로 DB에서 찾아와 반환
     */
    public UserEntity findByEmail(String email) {
        String sql = "select email, password from users where email = ?";
        UserEntity user = jdbcTemplate.queryForObject(
                sql, new Object[]{email}, (resultSet, rowNum) -> {
                    UserEntity userEntity = new UserEntity(
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                    return userEntity;
                });
        return user;
    }
}
