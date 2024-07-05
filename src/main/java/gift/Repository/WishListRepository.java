package gift.Repository;

import gift.Model.WishListItem;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void createTable() {
        String sql = """
                    CREATE TABLE if not exists wishlist (
                        user_id INT NOT NULL,
                        product_id INT NOT NULL,
                        count INT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id),  -- users 테이블의 id 칼럼과 외래키 관계
                        FOREIGN KEY (product_id) REFERENCES products(id)  -- products 테이블의 id 칼럼과 외래키 관계
                    );
                """;
        jdbcTemplate.execute(sql);
    }

    public List<WishListItem> getWishlist(int userId) {
        String sql = "SELECT * FROM Wishlist WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishListItem.class), userId);
    }

    public void addWishlistItem(int userId, int productId) {
        String sql = "INSERT INTO Wishlist (user_id, product_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, productId);
    }

    public void removeWishlistItem(int userId, int productId) {
        String sql = "DELETE FROM Wishlist WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, userId, productId);
    }
}
