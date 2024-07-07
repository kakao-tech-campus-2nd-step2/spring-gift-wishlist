package gift.repository;

import gift.model.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {


    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Member member) {
        String sql = "INSERT INTO members (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
    }

    @Override
    public Member findByEmail(String email) {
        var sql = "SELECT * FROM members WHERE email=?";
        try {
            return jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) -> new Member(
                    resultSet.getString("email"),
                    resultSet.getString("password")
                ),
                email
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Member findById(Long id) {
        var sql = "SELECT * FROM members WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) -> new Member(
                    resultSet.getString("email"),
                    resultSet.getString("password")
                ),
                id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
