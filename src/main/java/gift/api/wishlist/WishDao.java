package gift.api.wishlist;

import gift.api.member.Member;
import java.sql.Types;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class WishDao {

    private final JdbcClient jdbcClient;

    public WishDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Wish> getAllWishes(Member member) {
        return jdbcClient.sql("select * from wishlist where member_id = :id")
                        .param("id", member.getId(), Types.BIGINT)
                        .query(Wish.class)
                        .list();
    }
}
