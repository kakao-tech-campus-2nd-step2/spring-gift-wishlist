package gift.member;

import java.util.List;
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
            insert into member (id, name, email, password, role) 
            values (?,?,?,?,?)
            """;
        jdbcClient.sql(sql)
            .param(member.getId())
            .param(member.getName())
            .param(member.getEmail())
            .param(member.getPassword())
            .param(member.isRole())
            .update();
    }

    public Optional<Member> findMember(Member member) {
        var sql = """
            select email, password 
            from member 
            where email = ? AND password = ?
            """;
        return jdbcClient.sql(sql)
            .param(member.getEmail())
            .param(member.getPassword())
            .query(Member.class).optional();
    }

    public Optional<Member> findMemberById(Long userId) {
        var sql = """
            select * 
            from member 
            where id = ?
            """;
        return jdbcClient.sql(sql)
            .param(userId)
            .query(Member.class).optional();
    }

    public List<Member> findAllMember() {
        var sql = """
            select email, password
            from member
            """;
        List<Member> members = jdbcClient.sql(sql).query(Member.class).list();
        return members;
    }
}
