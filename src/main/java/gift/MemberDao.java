package gift;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
    @Autowired
    private JdbcClient jdbcClient;

    public MemberDao(JdbcClient jdbcClient) {this.jdbcClient = jdbcClient;}

    public void insertMember(Member member) {
        var sql = """
            insert into member (email, password) 
            values (?,?)
            """;
        jdbcClient.sql(sql)
            .param(member.getEmail())
            .param(member.getPassword())
            .update();
    }
}
