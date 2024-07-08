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

    public Boolean addWishProduct(String memberId, Long productId) {
        String sql = "INSERT INTO WISH_PRODUCT (member_id, product_id) " +
                "VALUES (:member_id, :product_id)";
        int resultRowNum = this.jdbcClient.sql(sql)
                .param("member_id", memberId)
                .param("product_id", productId)
                .update();
        return resultRowNum == 1;
    }

    public Boolean deleteWishProduct(String memberId, Long productId) {
        String sql = "DELETE FROM WISH_PRODUCT " +
                "WHERE member_id = :member_id AND product_id = :product_id";
        int resultRowNum = this.jdbcClient.sql(sql)
                .param("member_id", memberId)
                .param("product_id", productId)
                .update();
        return resultRowNum == 1;
    }

}
