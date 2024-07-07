package gift.repository;

import gift.dto.WishDto;
import gift.entity.Product;
import gift.service.WishService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.JDBCType;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<WishDto, Product> getAll(String token) {
        var sql = """
                    select w.id, p.id, w.token,p.name,p.price,p.url
                    from (wish as w 
                    join product as p
                    where w.productId=p.id) as wish_product
                    where w.token =? 
                    """;
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    WishDto wishDto= new WishDto(
                        resultSet.getInt("w.id"),
                        resultSet.getInt("p.id"),
                        resultSet.getString("w.token")
                );
                    Product product= new Product(
                        resultSet.getInt("p.id"),
                        resultSet.getString("p.name"),
                        resultSet.getInt("p.price"),
                        resultSet.getString("p.url")
                );
                    return new AbstractMap.SimpleEntry<>(wishDto, product);
                    },
                token
                ).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}



