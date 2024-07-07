package gift.repository;

import gift.model.WishList;
import gift.model.WishListItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public WishList getOrCreateWishList(Long memberId) {
        String sql = "SELECT * FROM wish_lists WHERE member_id = ?";
        List<WishList> wishLists = jdbcTemplate.query(sql, new WishListRowMapper(), memberId);

        if (wishLists.isEmpty()) {
            String insertSql = "INSERT INTO wish_lists (member_id) VALUES (?)";
            jdbcTemplate.update(insertSql, memberId);
            return getOrCreateWishList(memberId);
        }

        return wishLists.getFirst();
    }

    public void addItemToWishList(Long wishListId, Long productId, int quantity) {
        String sql = "INSERT INTO wish_list_items (wish_list_id, product_id, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, wishListId, productId, quantity);
    }

    public List<WishListItem> getWishListItems(Long wishListId) {
        String sql = "SELECT * FROM wish_list_items WHERE wish_list_id = ?";
        return jdbcTemplate.query(sql, new WishListItemRowMapper(), wishListId);
    }

    public void removeItemFromWishList(Long wishListId, Long productId) {
        String sql = "DELETE FROM wish_list_items WHERE wish_list_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, wishListId,productId);
    }

    public void changeItemNumberFromWishList(Long wishListId, Long productId,int quantity) {
        String sql = "UPDATE wish_list_items SET quantity = ? WHERE wish_list_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, quantity, wishListId, productId);
    }

    private static class WishListRowMapper implements RowMapper<WishList> {
        @Override
        public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new WishList(
                rs.getLong("id"),
                rs.getLong("member_id")
            );
        }
    }

    private static class WishListItemRowMapper implements RowMapper<WishListItem> {
        @Override
        public WishListItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new WishListItem(
                rs.getLong("id"),
                rs.getLong("wish_list_id"),
                rs.getLong("product_id"),
                rs.getInt("quantity")
            );
        }
    }

}