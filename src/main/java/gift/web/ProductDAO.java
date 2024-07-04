package gift.web;

import gift.web.dto.Product;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
    private JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertProduct(Product product) {
        var sql = "insert into products (name, price, image_url) values (?, ?, ?)";
        jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl());
    }

    public List<Product> selectAllProducts() {
        var sql = "select * from products";
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("image_url")
            )
        );
    }
    public Product selectProductById(long id) {
        var sql = "select * from products where id = ?";
        return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("image_url")
            ),
            id
        );
    }

    public void updateProduct(Product product) {
        var sql = "update products set name = ?, price = ?, image_url = ? where id = ?";
        jdbcTemplate.update(
            sql,
            product.name(),
            product.price(),
            product.imageUrl(),
            product.id()
        );
    }

    public Product deleteProductById(long id) {
        Product product = selectProductById(id);
        if (product != null) {
            var sql = "delete from products where id = ?";
            jdbcTemplate.update(sql, id);
            return product;
        }
        return null;
    }
}
