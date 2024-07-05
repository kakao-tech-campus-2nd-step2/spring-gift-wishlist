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
        dropWishListTable();
    }

    public void createTables() {
        createProductTable();
        createUserTable();
        createWishListTable();
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

    private void dropWishListTable() {
        var sql = "DROP TABLE wishes IF EXISTS";
        jdbcTemplate.execute(sql);
    }

    private void createWishListTable() {
        var sql = """
            create table wishes(
                product_name varchar(255),
                count varchar(255),
                user_id int
            )
            """;
        jdbcTemplate.execute(sql);
    }
}
