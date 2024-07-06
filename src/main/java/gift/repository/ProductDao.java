package gift.repository;

import gift.dto.CreateProduct;
import gift.dto.EditProduct;
import gift.dto.ProductDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {

        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }


    public long insert(CreateProduct.Request request) {
        Map<String, Object> parameters = Map.of(
                "name", request.getName(),
                "url", request.getImageUrl(),
                "price", request.getPrice()
        );
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        return newId.longValue();
    }

    public ProductDTO selectProduct(long id) {
        var sql = "select id, name, price, url from product where id= ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new ProductDTO(
                        id,
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")
                ),
                id
        );
    }

    public void updateProduct(long id, EditProduct.Request request){
        var sql = "update product set name= ?, price = ?, url = ? where id=?";
        jdbcTemplate.update(sql,request.getName(),request.getPrice(),request.getImageUrl(),id);
    }

    public void deleteProduct(long id){
        var sql = "delete from product where id=?";
        jdbcTemplate.update(sql,id);
    }
}
