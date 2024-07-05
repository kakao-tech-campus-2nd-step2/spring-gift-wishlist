package gift.dao;

import gift.model.member.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertMemeber(Member member) {
        var sql = "insert into members (email, password, role) values (?, ?, ?)";
        jdbcTemplate.update(sql, member.email(), member.password(),member.role());
    }
    public void updateMember(Member member){
        var sql = "update members set email = ? , password = ?, role = ? where email = ? ";
        jdbcTemplate.update(sql,member.password(), member.role(), member.email());
    }

    public List<Member> selectAllMembers() {
        var sql = "select email, password, role  from members";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Member(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                )
        );
    }
    public boolean isMemberExist(String email) {
        var sql = "select count(*) from members where email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }
}