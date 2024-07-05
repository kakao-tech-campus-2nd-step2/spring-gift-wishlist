package gift.repository;

import gift.model.WishList;
import java.util.HashMap;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("wishlist")
            .usingGeneratedKeyColumns("id");
        createWishListTable();
    }

    private void createWishListTable() {
        String sql = "CREATE TABLE IF NOT EXISTS wishlist (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "member_id BIGINT NOT NULL," +
            "product_id BIGINT NOT NULL," +
            "FOREIGN KEY (member_id) REFERENCES members(id)," +
            "FOREIGN KEY (product_id) REFERENCES products(id)" +
            ")";
        jdbcTemplate.execute(sql);
    }



    public List<WishList> findByMemberId(Long memberId) {
        String sql = "SELECT * FROM wish_list_items WHERE member_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            Long productId = rs.getLong("product_id");
            return new WishList(id, memberId, productId);
        }, memberId);
    }

    public WishList addWishListItem(WishList wishList) {
        var parameters = new HashMap<String, Object>();
        parameters.put("member_id", wishList.getMemberId());
        parameters.put("product_id", wishList.getProductId());
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        wishList.setId(newId.longValue());
        return wishList;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM wishlist WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}