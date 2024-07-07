package gift.Repository;

import gift.Model.Product;
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
                    CREATE TABLE if not exists Wishlist (
                        user_id INT NOT NULL,
                        product_id INT NOT NULL,
                        count INT NOT NULL,
                        price INT,
                        FOREIGN KEY (user_id) REFERENCES Users(id),  -- users 테이블의 id 칼럼과 외래키 관계
                        FOREIGN KEY (product_id) REFERENCES products(id)  -- products 테이블의 id 칼럼과 외래키 관계
                    );
                """;
        jdbcTemplate.execute(sql);
    }

    public List<WishListItem> getWishlist(int userId) {
        String sql = """
                    SELECT w.user_id, w.product_id, p.name AS productName, w.count, w.price
                    FROM Wishlist w
                    JOIN products p ON w.product_id = p.id
                    WHERE w.user_id = ?;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishListItem.class), userId);
    }

    public void addWishlistItem(WishListItem wishlistItem) {
        String sql = "INSERT INTO Wishlist (user_id, product_id, count, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wishlistItem.getUserId(), wishlistItem.getProductId(), wishlistItem.getCount(), wishlistItem.getPrice());
    }

    public void removeWishlistItem(WishListItem wishListItem) {
        int userId = wishListItem.getUserId();
        int productId = wishListItem.getProductId();
        int quantity = wishListItem.getCount();

        String checkCountSql = "SELECT count FROM Wishlist WHERE user_id = ? AND product_id = ?";
        Integer currentCount = jdbcTemplate.queryForObject(checkCountSql, new Object[]{userId, productId}, Integer.class);


        if (currentCount != null && currentCount > quantity) {
            String updateSql = "UPDATE Wishlist SET count = count - ? WHERE user_id = ? AND product_id = ?";
            jdbcTemplate.update(updateSql, quantity, userId, productId);
            updateSql = "SELECT price FROM products WHERE id = ?";
            List<Product> list = jdbcTemplate.query(updateSql, new BeanPropertyRowMapper<>(Product.class), productId);
            updateSql = "UPDATE Wishlist SET price = price - ? WHERE user_id = ? AND product_id = ?";
            jdbcTemplate.update(updateSql, list.get(0).getPrice(), userId, productId);
        } else if (currentCount != null) {
            String deleteSql = "DELETE FROM Wishlist WHERE user_id = ? AND product_id = ?";
            jdbcTemplate.update(deleteSql, userId, productId);
        }
    }
}
