package gift.api.member;

import java.sql.Types;
import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    private final JdbcClient jdbcClient;

    public MemberDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public boolean hasMemberByEmail(String email) {
        return jdbcClient.sql("select exists (select 1 from member where email = :email)")
                        .param("email", email, Types.VARCHAR)
                        .query(Boolean.class)
                        .single();
    }

    public boolean hasMemberByEmailAndPassword(MemberRequest memberRequest) {
        return jdbcClient.sql("select exists (select 1 from member where email = :email and password = :password)")
                        .param("email", memberRequest.email(), Types.VARCHAR)
                        .param("password", memberRequest.password(), Types.VARCHAR)
                        .query(Boolean.class)
                        .single();
    }

    public Optional<Member> getMemberByEmailAndPassword(String email, String password) {
        return jdbcClient.sql("select * from member where email = :email and password = :password")
                        .param("email", email, Types.VARCHAR)
                        .param("password", password, Types.VARCHAR)
                        .query(Member.class)
                        .optional();
    }

    public void insert(MemberRequest memberRequest) {
        jdbcClient.sql("insert into member (email, password, role) values (:email, :password, :role)")
            .param("email", memberRequest.email(), Types.VARCHAR)
            .param("password", memberRequest.password(), Types.VARCHAR)
            .param("role", memberRequest.role(), Types.VARCHAR)
            .update();
    }
}
