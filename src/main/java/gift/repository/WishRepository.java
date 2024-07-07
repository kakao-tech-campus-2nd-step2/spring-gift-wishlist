package gift.repository;

import gift.service.WishService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.JDBCType;
import java.util.Map;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public WishRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("wish")
                .usingGeneratedKeyColumns("id");
    }

    public ResponseEntity<Map<String, Object>> save(Long productId, String token) {
        Map<String, Object> parameters = Map.of(
                "productId",productId,
                "token",token
        );
        Number newId= simpleJdbcInsert.executeAndReturnKey(parameters);
        long id= newId.longValue();
        System.out.println("위시리스트에 1개의 상품이 추가되었습니다.");
        parameters.put("id",id);
        return ResponseEntity.ok()
                .header("Authorization","Basic"+ token)
                .contentType(MediaType.APPLICATION_JSON)
                .body(parameters);
    }
}
