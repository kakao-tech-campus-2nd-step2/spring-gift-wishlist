package gift.product;

import gift.product.model.GetProductRes;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GetProductRes findProduct(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, price, image_url FROM product WHERE id = ?",
                (rs, rowNum) -> new GetProductRes(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("image_url")
                ),
                id
        );
    }

    public List<GetProductRes> findAllProduct() {
        return jdbcTemplate.query(
                "SELECT id, name, price, image_url FROM product",
                (rs, rowNum) -> new GetProductRes(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("image_url")
                )
        );
    }
}
