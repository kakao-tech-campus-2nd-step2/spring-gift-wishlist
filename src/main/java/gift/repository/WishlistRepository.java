package gift.repository;

import gift.dto.user.WishProductDto;
import gift.model.Product;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepository {
    private final JdbcTemplate jdbcTemplate;
    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WishProductDto> findByUserId(Long userId) {
        String sql = "SELECT p.id, p.name, p.price, p.imageUrl, w.product_count "
                + "FROM wishlist w "
                + "JOIN product p ON w.product_id = p.id "
                + "WHERE w.user_id = ?";

        return jdbcTemplate.query(sql, wishProductRowMapper, userId);
    }

    private final RowMapper<WishProductDto> wishProductRowMapper = (rs, rowNum) -> {
        Product product = new Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("imageUrl")
        );
        return new WishProductDto(product, rs.getInt("product_count"));
    };
}
