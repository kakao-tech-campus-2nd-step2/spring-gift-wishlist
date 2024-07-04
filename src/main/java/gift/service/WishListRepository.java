package gift.service;

import gift.model.Product;
import gift.model.ProductDTO;
import gift.model.WishList;
import gift.model.WishListDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(WishListService.class);


    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createWishList(WishListDTO wishListDTO) {
        String sql = "INSERT INTO wishlist (email, product_name, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, wishListDTO.email(), wishListDTO.product_name(), wishListDTO.quantity());
    }

    public List<WishList> getAllWishList() {
        String sql = "SELECT * FROM wishlist";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishList.class));
    }

    public List<WishList> getWishListByEmail(String email) {
        String sql = "SELECT * FROM wishlist WHERE email = ?";
        logger.info("Querying database for wish list with email: {}", email);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishList.class), email);
    }
}
