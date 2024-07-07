package gift.repository;

import gift.dto.UserDto;
import gift.entity.Product;
import gift.entity.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public UserRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("uuser")
                .usingGeneratedKeyColumns("id");
    }
    public ResponseEntity<Long> save(UserDto.Request request) {
        Map<String, Object> parameters = Map.of(
                "userEmail",request.getUserEmail() ,
                "userPassword", request.getUserPassword()
        );
        Number newId= simpleJdbcInsert.executeAndReturnKey(parameters);
        long id = newId.longValue();
        ResponseEntity<Long> responseEntity =new ResponseEntity<>(id, HttpStatusCode.valueOf(201));
        System.out.println("회원가입 완료");
        return responseEntity;
    }

    public List<UserDto> getAll() {
        var sql = "select * from uuser";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new UserDto(
                        resultSet.getInt("id"),
                        resultSet.getString("userEmail"),
                        resultSet.getString("userPassword")
                )
        );
    }

    public UserDto findByUserEmail(String userEmail) {
        var sql = "select * from uuser where userEmail=?";
        List<UserDto> results = jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new UserDto(
                        resultSet.getLong("id"),
                        resultSet.getString("userEmail"),
                        resultSet.getString("userPassword")
                ),
                userEmail
        );
        return results.isEmpty() ? null : results.get(0);

    }

}
