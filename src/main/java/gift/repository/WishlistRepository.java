package gift.repository;

import gift.domain.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Product> findAllProducts(Long memberId) {
        String sql = "SELECT p.id, p.name, p.price, p.image_url FROM wishlist AS w INNER JOIN product AS p ON w.product_id = p.id WHERE w.member_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
            ),
            memberId
        );
    }

    public void saveProduct(Long memberId, Long productId) {
        String sql = "INSERT INTO wishlist (member_id, product_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, memberId, productId);
    }

}
