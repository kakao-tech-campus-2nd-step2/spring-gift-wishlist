package gift.service;

import gift.model.WishList;
import gift.model.WishListDTO;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WishList> getWishListByEmail(String email) {
        String sql = "SELECT product_name, quantity FROM wishlist WHERE email = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishList.class), email);
    }

}
