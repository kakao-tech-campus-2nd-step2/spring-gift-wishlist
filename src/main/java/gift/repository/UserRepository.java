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
    public ResponseEntity<Long> save(UserDto userDto) {
        Map<String, Object> parameters = Map.of(
                "userEmail",userDto.getUserEmail() != null ? userDto.getUserEmail() : "default@naver.com",
                "userPassword", userDto.getUserPassword() != null ? userDto.getUserPassword() : "1234"
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

}
