package gift.Repository;

import gift.Model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbctemplate;

    public ProductRepository(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    public void createProductTable() {
        var sql = """
                create table Products(
                id bigint auto_increment,
                name varchar(255),
                price bigint,
                imageUrl varchar(2000),
                primary key(id)
                )
                """;
        jdbctemplate.execute(sql);
    }

    public void insertProduct(Product product) {
        var sql = "insert into Products (name, price, imageUrl) values (?,?,?)";
        jdbctemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public List<Product> selectProduct() {
        var sql = "select * from products";
        return jdbctemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product selectProductById(Long id) {
        var sql = "SELECT * FROM products WHERE id = ?";
        return jdbctemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);

    }

    public void updateProduct(Long id, Product product) {
        var sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbctemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    public void deleteProduct(Long id) {
        var sql = "DELETE FROM products WHERE id = ?";
        jdbctemplate.update(sql, id);
    }
}
