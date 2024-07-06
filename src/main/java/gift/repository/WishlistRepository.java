package gift.repository;

import gift.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WishlistRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createWishlistTable();
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("wishlist_items")
            .usingGeneratedKeyColumns("id");
    }

    public void createWishlistTable() {
        String sql = "CREATE TABLE IF NOT EXISTS wishlist_items (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "member_id BIGINT NOT NULL," +
            "product_id BIGINT NOT NULL," +
            "product_name VARCHAR(255) NOT NULL," +
            "product_price BIGINT NOT NULL" +
            ")";
        jdbcTemplate.execute(sql);
    }

}
