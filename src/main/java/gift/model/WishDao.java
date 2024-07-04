package gift.model;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishDao {
    private final JdbcClient jdbcClient;


    public WishDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void save(Long productId, Long memberId) {
        var sql = "insert into wish(product_id, member_id) values(?, ?)";
        jdbcClient.sql(sql)
                .params(productId, memberId)
                .update();
    }

}
