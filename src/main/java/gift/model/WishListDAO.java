package gift.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * WishListDAO 클래스는 WishList 객체를 관리하는 DAO 클래스입니다. DB를 이용해 WishList 객체를 관리할 수 있습니다.
 */
@Repository
public class WishListDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertWishList;

    public WishListDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.insertWishList = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("wishlist")
            .usingGeneratedKeyColumns("id");
    }

    /**
     * 새로운 WishList를 생성합니다.
     *
     * @param productId WishList에 추가할 상품의 ID
     * @param userId    WishList에 추가할 사용자의 ID
     * @return 생성된 WishList 객체
     */
    public WishList createWishList(long productId, long userId) {
        WishList wishList = new WishList(productId, userId);
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(wishList);
        Number newId = insertWishList.executeAndReturnKey(parameters);
        return new WishList(newId.longValue(), wishList.getProductId(), wishList.getUserId());
    }

    /**
     * 지정된 사용자의 모든 WishList를 조회합니다.
     *
     * @param userId 조회할 사용자의 ID
     * @return 지정된 사용자의 모든 WishList 객체의 리스트
     */
    public List<WishList> getWishListsByUserId(long userId) {
        String sql = "SELECT * FROM wishlist WHERE user_id = :userId";
        return jdbcTemplate.query(sql,
            new MapSqlParameterSource("userId", userId),
            (rs, rowNum) -> new WishList(rs.getLong("id"), rs.getLong("product_id"),
                rs.getLong("user_id")));
    }

    /**
     * 모든 WishList를 조회합니다.
     *
     * @return 모든 WishList 객체의 리스트
     */
    public List<WishList> getAllWishLists() {
        String sql = "SELECT * FROM wishlist";
        return jdbcTemplate.query(sql,
            (rs, rowNum) -> new WishList(rs.getLong("id"), rs.getLong("product_id"),
                rs.getLong("user_id")));
    }

    /**
     * 지정된 사용자의 모든 WishList를 삭제합니다.
     *
     * @param userId 삭제할 사용자의 ID
     */
    public void deleteWishListsByUserId(Long userId) {
        String sql = "DELETE FROM wishlist WHERE user_id = :userId";
        jdbcTemplate.update(sql, new MapSqlParameterSource("userId", userId));
    }

    /**
     * 지정된 사용자가 지정된 상품을 위시리스트에서 삭제합니다.
     *
     * @param userId 삭제할 사용자의 ID
     * @param productId 삭제할 상품의 ID
     */
    public void deleteWishListByUserIdAndProductId(Long userId, Long productId) {
        String sql = "DELETE FROM wishlist WHERE user_id = :userId AND product_id = :productId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);
        params.addValue("productId", productId);
        jdbcTemplate.update(sql, params);
    }
}
