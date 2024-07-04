package gift.model;

import gift.controller.dto.request.WishRequest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishDao {
    private final JdbcClient jdbcClient;


    public WishDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void save(WishRequest request, Long memberId) {
        var sql = "insert into wish(product_id, product_count, member_id) values(?, ?, ?)";
        jdbcClient.sql(sql)
                .params(request.productId(), request.productCount(), memberId)
                .update();
    }

    public List<Wish> findAllByMemberId(Long memberId) {
        var sql = "select * from wish w left join product p " +
                "on w.product_id = p.id where w.member_id = ?";
        return jdbcClient.sql(sql)
                .params(memberId)
                .query(new WishRowMapper())
                .list();
    }

    public void deleteById(Long id, Long memberId) {
        var sql = "delete from wish where id = ? and member_id = ?";
        jdbcClient.sql(sql)
                .params(id, memberId)
                .update();
    }
}
