package gift.dao;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import gift.domain.User;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findByPassword(String password){
        try{
            var sql = "select id, name, password, email from users where password = ?";
            User user = jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNum) -> new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("email")),
                    password
            );

            return Optional.ofNullable(user);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void insertUser(User user) {

        var sql = "INSERT INTO users (id, name, password, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getPassword(), user.getEmail());

    }

    public void updateUser(User user) {
        
        var sql = "UPDATE users SET name = ?, password = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getEmail(), user.getId());

    }

    public void deleteUser(Long id) {

        var sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
