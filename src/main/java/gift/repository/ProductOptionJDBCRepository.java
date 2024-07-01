package gift.repository;

import gift.model.ProductOption;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;

public class ProductOptionJDBCRepository implements ProductOptionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public ProductOptionJDBCRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("product_option")
                .usingGeneratedKeyColumns("id");
    }

    public ProductOption save(ProductOption productOption) {
        var param = new BeanPropertySqlParameterSource(productOption);
        Number key = jdbcInsert.executeAndReturnKey(param);
        ProductOption result = new ProductOption(key.longValue(), productOption.getProductId(), productOption.getName(), productOption.getAdditionalPrice());
        return result;
    }

    public void update(ProductOption productOption) {
        var sql = "update product_option set name=?, additional_price=? where id = ?";
        jdbcTemplate.update(sql, productOption.getName(), productOption.getAdditionalPrice(), productOption.getId());
    }

    public ProductOption findById(Long id) {
        var sql = "select id, product_id, name, additional_price from product_option where id = ?";
        ProductOption productOption = jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) ->
                        new ProductOption(
                                resultSet.getLong("id"),
                                resultSet.getLong("product_id"),
                                resultSet.getString("name"),
                                resultSet.getInt("additional_price")
                        ), id);
        return productOption;
    }

    public List<ProductOption> findAll(Long productId) {
        var sql = "select id, product_id, name, additional_price from product_option where product_id = ?";
        List<ProductOption> products = jdbcTemplate.query(
                sql,
                (resultSet, rowNum) ->
                        new ProductOption(
                                resultSet.getLong("id"),
                                resultSet.getLong("product_id"),
                                resultSet.getString("name"),
                                resultSet.getInt("additional_price")
                        ), productId);
        return products;
    }

    public void deleteById(Long id) {
        var sql = "delete from product_option where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteByProductId(Long id) {
        var sql = "delete from product_option where product_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
