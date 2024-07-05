package wishlist.repository;


import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import wishlist.exception.CustomException.UserNotFoundException;
import wishlist.exception.ErrorCode;
import wishlist.model.user.User;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(User user) {
        var sql = "insert into users (email,password) values (?,?)";
        jdbcTemplate.update(sql,user.getEmail(),user.getPassWord());
    }

    public User findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(
                "select * from users where email =?",
                (rs,row)-> new User(
                    rs.getString("email"),
                    rs.getString("password")
                ),
                email
            );
            return user;
        }catch (Exception e){
            throw new UserNotFoundException(ErrorCode.EMAIL_NOT_FOUND);
        }
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
            "select* from users",
            (rs, rowNum) -> new User(
                rs.getString("email"),
                rs.getString("password")
            )
        );
    }

    public void delete(String email) {
        jdbcTemplate.update("delete from users where email = ?",email);
    }

}
