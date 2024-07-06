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

    public void insert(WishListRequestDto wishListRequestDto, Member member) {
        jdbcClient.sql("insert into wishlist (memberId, productId, quantity) values (:memberId, :productId, :quantity)")
            .param("memberId", member.getId(), Types.BIGINT)
            .param("productId", wishListRequestDto.productId(), Types.BIGINT)
            .param("quantity", wishListRequestDto.quantity(), Types.INTEGER)
            .update();
    }

    public void update(WishListRequestDto wishListRequestDto, Member member) {
        jdbcClient.sql("update wishlist set quantity = :quantity where memberId = :memberId and productId = :productId")
            .param("quantity", wishListRequestDto.quantity(), Types.INTEGER)
            .param("memberId", member.getId(), Types.BIGINT)
            .param("productId", wishListRequestDto.productId(), Types.BIGINT)
            .update();
    }
}
