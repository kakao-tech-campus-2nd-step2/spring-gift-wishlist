package gift.model;

import gift.controller.dto.request.SignInRequest;
import gift.controller.dto.request.SignUpRequest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberDao {
    private final JdbcClient jdbcClient;

    public MemberDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void save(SignUpRequest request) {
        var sql = "insert into member (email, password) values (?, ?)";
        jdbcClient.sql(sql)
                .params(request.email(), request.password())
                .update();
    }

    public Optional<Member> findById(Long id) {
        var sql = "select * from member where id = ?";
        return jdbcClient.sql(sql)
                .params(id)
                .query(Member.class)
                .optional();
    }

    public Optional<Member> findByEmailAndPassword(SignInRequest request) {
        var sql = "select * from member where email = ? and password = ?";
        return jdbcClient.sql(sql)
                .params(request.email(), request.password())
                .query(Member.class)
                .optional();
    }

    public boolean existsByEmail(String email) {
        var sql = "select count(*) from member where email = ?";
        int count = jdbcClient.sql(sql)
                .params(email)
                .query(Integer.class)
                .single();
        return count > 0;
    }
}
