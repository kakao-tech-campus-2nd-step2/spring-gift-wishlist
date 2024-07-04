package gift.service;

import gift.model.Member;
import gift.model.MemberDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Member createMember(MemberDTO memberDTO) {
        String sql = "INSERT INTO members (password, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, memberDTO.password(),memberDTO.email());
        return getByEmail(memberDTO.email());
    }

    public Member getByEmail(String email) {
        String sql = "SELECT * FROM members WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), email);
    }

    public Member getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM members WHERE email = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), email, password);
    }

}
