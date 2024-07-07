package gift.domain.member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Member findByEmail(String email){
        String sql = "SELECT * FROM member where email = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            return new Member(id, email, name, password, role);
        }, email);
    }

    public boolean isNotExistMember(String email){
        String sql = "SELECT * FROM member WHERE email=?";
        return jdbcTemplate.query(sql, (rs,rowNum)-> 0, email).isEmpty();
    }

    public boolean isNotExistMemberById(Long id){
        String sql = "SELECT * FROM member WHERE id=?";
        return jdbcTemplate.query(sql, (rs,rowNum)-> 0, id).isEmpty();
    }
}
