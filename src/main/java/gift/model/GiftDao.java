package gift.model;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class GiftDao {
    private final JdbcTemplate jdbcTemplate;

    public GiftDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Gift gift) {
        String sql = "INSERT INTO gift (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, gift.getName(), gift.getPrice(), gift.getImageUrl());
    }

    public Gift findById(Long id) {
        try {
            String sql = "SELECT * FROM gift WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    new Gift(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("imageUrl")
                    ), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchElementException();
        }
    }

    public List<Gift> findAll() {
        try {
            String sql = "SELECT * FROM gift";
            return jdbcTemplate.query(sql, (rs, rowNum) ->
                    new Gift(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("imageUrl")
                    ));
        } catch (EmptyResultDataAccessException ex) {
            return Collections.emptyList();
        }
    }

    public void updateById(Gift gift, Long id) {
        try {
            String sql = "UPDATE gift SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
            jdbcTemplate.update(sql, gift.getName(), gift.getPrice(), gift.getImageUrl(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchElementException();
        }
    }

    public void deleteById(Long id) {
        try {
            String sql = "DELETE FROM gift WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchElementException();
        }
    }
}
