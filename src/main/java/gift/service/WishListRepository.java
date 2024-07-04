package gift.service;

import gift.model.WishList;
import gift.model.WishListDTO;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;


    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public WishList createWishList(WishListDTO wishListDTO) {
        String sql = "INSERT INTO wishlist (email, product_name, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, wishListDTO.email(), wishListDTO.product_name(),
            wishListDTO.quantity());
        return getWishListByProductName(wishListDTO.product_name());
    }

    public WishList getWishListByProductName(String product_name) {
        String sql = "SELECT * FROM wishlist WHERE product_name = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(WishList.class),
            product_name);
    }

    public List<WishList> getAllWishList() {
        String sql = "SELECT * FROM wishlist";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishList.class));
    }

    public List<WishList> getWishListByEmail(String email) {
        String sql = "SELECT * FROM wishlist WHERE email = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishList.class), email);
    }

    public boolean deleteWishList(String email, String product_name) {
        String sql = "DELETE FROM wishlist WHERE email = ? AND product_name = ?";
        return jdbcTemplate.update(sql, email, product_name) > 0;
    }
}
