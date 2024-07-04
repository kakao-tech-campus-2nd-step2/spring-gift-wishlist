package gift.product.dao;

import gift.product.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createMemberTable() {
        System.out.println("[MemberDao] createMemberTable()");
        var sql = """
            create table member_list (
              email varchar(255) not null,
              password varchar(255) not null,
              token varchar(255),
              rank int,
              primary key (email)
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public void registerMember(Member member) {
        System.out.println("[MemberDao] registerMember()");
        var sql = "insert into member_list (email, password) values (?, ?)";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
    }

}
