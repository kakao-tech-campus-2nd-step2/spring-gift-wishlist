package gift.utility;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataBaseUtil {

    private final JdbcTemplate jdbcTemplate;

    public DataBaseUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void dropTables() {
        dropProductTable();
        dropUserTable();
    }

    public void createTables() {
        createProductTable();
        createUserTable();
    }

    private void dropProductTable() {
        var sql = "DROP TABLE products IF EXISTS";
        jdbcTemplate.execute(sql);
    }

    private void createProductTable() {
        var sql = """
            create table products(
                id bigint,
                name varchar(255),
                price int,
                image_url varchar(255),
                primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }

    private void dropUserTable() {
        var sql = "DROP TABLE users IF EXISTS";
        jdbcTemplate.execute(sql);
    }

    private void createUserTable() {
        var sql = """
            create table users(
                id bigint AUTO_INCREMENT,
                email varchar(255),
                password varchar(255),
                name varchar(255),
                role varchar(255),
                primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }
}
