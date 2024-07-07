package gift.repository;

import gift.dto.CreateProduct;
import gift.dto.EditProduct;
import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
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

    public List<Product> findAll() {
        var sql = "select * from product";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")

                )
        );
    }

    public Product findOneById(int id) {
        var sql = "select name,price,url from product where id=?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Product(
                        id,
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")
                ),
                id
        );
    }

    public void update(int id, EditProduct.Request request) {
        var sql = "update product set name=?, price=?, url=? where id=?";
        jdbcTemplate.update(sql, request.getName(), request.getPrice(), request.getImageUrl(), id);
    }

    public void delete(int id) {
        var sql = "delete from product where id= ?";
        jdbcTemplate.update(sql, id);
    }

}
