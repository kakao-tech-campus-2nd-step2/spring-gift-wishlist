package gift.api.wishlist;

import gift.api.member.Member;
import java.sql.Types;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class WishListDao {

    private final JdbcClient jdbcClient;

    public WishListDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<WishList> getAllWishes(Member member) {
        return jdbcClient.sql("select * from wishlist where memberId = :memberId")
                        .param("memberId", member.getId(), Types.BIGINT)
                        .query(WishList.class)
                        .list();
    }

    public void insert(WishListRequest wishListRequest, Member member) {
        jdbcClient.sql("insert into wishlist (memberId, productId, quantity) values (:memberId, :productId, :quantity)")
            .param("memberId", member.getId(), Types.BIGINT)
            .param("productId", wishListRequest.productId(), Types.BIGINT)
            .param("quantity", wishListRequest.quantity(), Types.INTEGER)
            .update();
    }

    public void update(WishListRequest wishListRequest, Member member) {
        jdbcClient.sql("update wishlist set quantity = :quantity where memberId = :memberId and productId = :productId")
            .param("quantity", wishListRequest.quantity(), Types.INTEGER)
            .param("memberId", member.getId(), Types.BIGINT)
            .param("productId", wishListRequest.productId(), Types.BIGINT)
            .update();
    }

    public void delete(WishListRequest wishListRequest, Member member) {
        jdbcClient.sql("delete from wishlist where memberId = :memberId and productId = :productId")
            .param("memberId", member.getId(), Types.BIGINT)
            .param("productId", wishListRequest.productId(), Types.BIGINT)
            .update();
    }
}
