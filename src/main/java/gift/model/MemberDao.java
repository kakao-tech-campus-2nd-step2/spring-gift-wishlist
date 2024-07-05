package gift.model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapper<Member> MemberRowMapper(){
        return ((resultSet, rowNum) -> {
            Member member = new Member();
            member.setEmail(resultSet.getString("email"));
            member.setPassword(resultSet.getString("password"));
            return member;
        });
    }

    public Member selectMember(String email){
        var sql = "select email, password from member where email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, MemberRowMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void insertMember(Member member){
        var sql = "insert into member (email, password) values (?, ?)";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
    }
}
