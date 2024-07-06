package gift.dao;

import gift.vo.WishProduct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JdbcClient를 이용한 DB 접속
 */
@Repository
public class WishlistDao {

    private final JdbcClient jdbcClient;

    public WishlistDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<WishProduct> getWishProductList(String memberId) {
        String sql = "SELECT * FROM wish_product WHERE member_id = :memberId";
        List<WishProduct> list = jdbcClient.sql(sql)
                .param("memberId", memberId)
                .query(new WishProductMapper()).list();
        return list;
    }
}
