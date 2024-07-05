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
        String sql = "SELECT p.name, p.price, p.image_url FROM wishlist w INNER JOIN product p ON w.product_id = p.id WHERE w.member_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
            ),
            memberId
        );
    }

}
