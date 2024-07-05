package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS wish_list_products");
            jdbcTemplate.execute("DROP TABLE IF EXISTS wish_lists");
            jdbcTemplate.execute("DROP TABLE IF EXISTS products");
            jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        } catch (Exception e) {
            System.out.println("테이블 삭제 중 오류 발생: " + e.getMessage());
        }

        jdbcTemplate.execute("CREATE TABLE users(" +
            "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "email VARCHAR(255) UNIQUE, " +
            "password VARCHAR(255))");

        jdbcTemplate.execute("CREATE TABLE products(" +
            "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(255), " +
            "price INT, " +
            "image_url VARCHAR(255))"); // image_url 컬럼 정의

        jdbcTemplate.execute("CREATE TABLE wish_lists(" +
            "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "user_id BIGINT, " +
            "FOREIGN KEY (user_id) REFERENCES users(id))");

        jdbcTemplate.execute("CREATE TABLE wish_list_products(" +
            "wish_list_id BIGINT, " +
            "product_id BIGINT, " +
            "FOREIGN KEY (wish_list_id) REFERENCES wish_lists(id), " +
            "FOREIGN KEY (product_id) REFERENCES products(id), " +
            "PRIMARY KEY (wish_list_id, product_id))");
    }
}