package gift.dao;

import gift.config.ProductDatabaseProperties;
import gift.entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final ProductDatabaseProperties productDatabaseProperties;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, ProductDatabaseProperties productDatabaseProperties) {
        this.jdbcTemplate = jdbcTemplate;
        this.productDatabaseProperties = productDatabaseProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        var sqlDropTable = "DROP TABLE IF EXISTS userDB";

        var sqlCreateTable = """
                CREATE TABLE userDB (
                    email VARCHAR(255) PRIMARY KEY,
                    password VARCHAR(50) NOT NULL
                );
                """;

        jdbcTemplate.execute(sqlDropTable);
        jdbcTemplate.execute(sqlCreateTable);
    }

    @ConfigurationProperties(prefix = "spring.datasource.user")
    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(productDatabaseProperties.getUrl(),
                productDatabaseProperties.getUsername(), productDatabaseProperties.getPassword());
    }

    public void insertUser(User user) {
        var sql = "INSERT INTO userDB (email, password) values (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    public Integer countUser(User user) {
        var sql = "SELECT COUNT(*) FROM userDB WHERE email = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, user.getEmail(), user.getPassword());
    }

}
