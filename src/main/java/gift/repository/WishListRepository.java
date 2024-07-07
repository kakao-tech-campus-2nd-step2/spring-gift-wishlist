package gift.repository;

import gift.model.Product;
import gift.model.WishList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<WishList> readWishList(Long userId) {
        String sql = "SELECT * FROM wishlist WHERE userid = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(WishList.class), userId);
    }

    public void addProductToWishList(Long userId, Product product) {
        String sql = "INSERT INTO wishlist (userid, product_name, product_price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userId, product.getName(), product.getPrice());
    }

    public void removeWishList(Long userId) {
        String sql = "DELETE FROM wishlist WHERE userid = ?";
        jdbcTemplate.update(sql, userId);
    }

    public void removeProductFromWishList(Long userId, Long wishId) {
        String sql = "DELETE FROM wishlist WHERE userid = ? AND wishid = ?";
        jdbcTemplate.update(sql, userId, wishId);
    }

}
