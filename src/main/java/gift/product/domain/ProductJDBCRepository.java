package gift.product.domain;

import gift.exception.type.DataAccessException;
import gift.product.application.command.ProductCreateCommand;
import gift.product.application.command.ProductUpdateCommand;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductJDBCRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<Product> productRowMapper;

    public ProductJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
        this.productRowMapper = productRowMapper();
    }

    @Override
    public List<Product> findAll() {
        try {
            String sql = "SELECT * FROM product";
            return jdbcTemplate.query(sql, productRowMapper);
        } catch (Exception e) {
            throw new DataAccessException("모든 상품을 조회하는 중에 문제가 발생했습니다.");
        }
    }

    @Override
    public Optional<Product> findById(Long productId) {
        try {
            String sql = "SELECT * FROM product WHERE id = ?";
            return jdbcTemplate.query(sql, productRowMapper, productId)
                    .stream()
                    .findFirst();
        } catch (Exception e) {
            throw new DataAccessException("특정 상품을 조회하는 중에 문제가 발생했습니다.");
        }
    }

    @Override
    public void addProduct(ProductCreateCommand productCreateCommand) {
        try {
            simpleJdbcInsert.execute(
                    Map.of(
                            "name", productCreateCommand.name(),
                            "price", productCreateCommand.price(),
                            "imageUrl", productCreateCommand.imageUrl()
                    )
            );
        } catch (Exception e) {
            throw new DataAccessException("상품을 추가하는 중에 문제가 발생했습니다.");
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        try {
            String sql = "DELETE FROM product WHERE id = ?";
            jdbcTemplate.update(sql, productId);
        } catch (Exception e) {
            throw new DataAccessException("상품을 삭제하는 중에 문제가 발생했습니다.");
        }
    }

    public void updateProduct(ProductUpdateCommand command) {
        try {
            String sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    command.name(),
                    command.price(),
                    command.imageUrl(),
                    command.productId()
            );
        } catch (Exception e) {
            throw new DataAccessException("상품을 수정하는 중에 문제가 발생했습니다.");
        }
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl"));
    }
}
