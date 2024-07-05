package gift.member.repository;

import gift.member.domain.Member;
import gift.product.domain.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class MemberRepository {
    private final JdbcClient jdbcClient;

    public MemberRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void save(Member member) {
        Assert.notNull(member, "Member must not be null");
        if (member.checkNew()) {
            String sql = "INSERT INTO members (email, password, nickname) VALUES (?, ?, ?)";
            jdbcClient.sql(sql)
                    .param(member.getEmail().getValue())
                    .param(member.getPassword().getValue())
                    .param(member.getNickName().getValue())
                    .update();

        }
        if (!member.checkNew()) {
            String sql = "UPDATE products SET email = ?, password = ?, nickname = ? WHERE id = ?";
            jdbcClient.sql(sql)
                    .param(member.getEmail().getValue())
                    .param(member.getPassword().getValue())
                    .param(member.getNickName().getValue())
                    .update();
        }
    }
}
