package gift.wish.repository;

import gift.product.domain.Product;
import gift.wish.domain.Wish;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {
    private final JdbcClient jdbcClient;

    public WishRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Wish> findAll(Long memberId) {
        String sql = "select * from wishes where member_id = ?";
        return jdbcClient.sql(sql)
                .param(memberId)
                .query(Wish.class)
                .list();
    }
}
