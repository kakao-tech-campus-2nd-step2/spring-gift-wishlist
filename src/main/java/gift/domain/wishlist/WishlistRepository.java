package gift.domain.wishlist;

import gift.domain.wishlist.dto.WishlistAddResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Wishlist> getRowMapper() {
        return (resultSet, rowNum) -> new Wishlist(
            resultSet.getLong("product_id"),
            resultSet.getString("user_email"),
            resultSet.getLong("quantity")
        );
    }

    public WishlistAddResponseDto save(Wishlist wishlist) {
        try {
            String sqlFind = "SELECT * FROM wishlist WHERE product_id = ? AND user_email = ?";
            Wishlist result = jdbcTemplate.queryForObject(sqlFind, getRowMapper(), wishlist.productId(), wishlist.userEmail());

            //수량은 최소한 0 이상이어야 함
            Long resultQuantity = Math.max(0, wishlist.quantity() + result.quantity());
            String sqlUpdate = "UPDATE wishlist SET quantity = ? WHERE product_id = ? AND user_email = ?";
            jdbcTemplate.update(sqlUpdate, resultQuantity, wishlist.productId(), wishlist.userEmail());
            return new WishlistAddResponseDto("add", resultQuantity);

        } catch (EmptyResultDataAccessException e) {
            String sqlInsert = "INSERT INTO wishlist (product_id, user_email, quantity) values (?, ?, ?)";
            jdbcTemplate.update(sqlInsert, wishlist.productId(), wishlist.userEmail(), wishlist.quantity());
            return new WishlistAddResponseDto("create", wishlist.quantity());
        }
    }

}
