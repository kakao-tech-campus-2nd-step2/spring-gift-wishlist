package gift.service;

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

    public void createMember(MemberDTO memberDTO) {
        String sql = "INSERT INTO members (password, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, memberDTO.getPassword(), memberDTO.getEmail());
    }

    public MemberDTO getByEmail(String email) {
        String sql = "SELECT * FROM members WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(MemberDTO.class), email);
    }

}
