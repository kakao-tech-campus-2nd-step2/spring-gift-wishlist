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

    public List<Wish> findAll() {
        String sql = "select * from wishes";
        return jdbcClient.sql(sql)
                .query(Wish.class)
                .list();
    }
}
