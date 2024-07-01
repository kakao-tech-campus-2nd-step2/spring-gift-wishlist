package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Override
    public void run(String... args) {
        jdbcTemplate.execute("DROP TABLE products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE products ("
            + "id LONG,"
            + " name VARCHAR(255),"
            + " price INT,"
            + " imageUrl VARCHAR(255),"
            + " PRIMARY KEY (id))"
        );
    }
}
