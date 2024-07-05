package gift.repository;

import gift.dto.Member;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initialize() {
        createMemberTable();
        registerMember(new Member("test@test.com", "1234"));

    }


    private void createMemberTable() {
        var sql = """
                create table member (
                  id bigint AUTO_INCREMENT,
                  email varchar(255),
                  password varchar(255),
                  primary key (id)
                )
                """;
        jdbcTemplate.execute(sql);
    }
    //add, get, update , delete
    public Long registerMember(Member member) {
        String sql = "INSERT INTO member (email, password) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, member.getEmail());
            ps.setString(2, member.getPassword());
            return ps;
        }, keyHolder);
        if (rowsAffected > 0) {
            return keyHolder.getKey().longValue();
        }
        return -1L;
    }
    public boolean checkEmailDuplicate(String email){
        String sql = "SELECT COUNT(*) FROM member WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
    //SELECT COUNT(*) FROM users WHERE id = :id AND password = :password"
    public Member getMemberIdByEmailAndPassword(Member member) {
        String sql = String.format("select id, email, password from member where email = '%s' and password = '%s'", member.getEmail(), member.getPassword());
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql, new UserRowMapper());
    }



    private static class UserRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setEmail(rs.getString("email"));
            member.setPassword(rs.getString("password"));
            return member;
        }
    }
}
