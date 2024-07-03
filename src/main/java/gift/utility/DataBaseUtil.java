package gift.utility;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataBaseUtil {

    private final JdbcTemplate jdbcTemplate;

    public DataBaseUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void dropProductTable() {
        var sql = "DROP TABLE products IF EXISTS";
        jdbcTemplate.execute(sql);
    }

    public void createProductTable() {
        var sql = """
            create table products(
                id bigint,
                name varchar(255),
                price int,
                image_url varchar(255)
            )
            """;
        jdbcTemplate.execute(sql);
    }
}
