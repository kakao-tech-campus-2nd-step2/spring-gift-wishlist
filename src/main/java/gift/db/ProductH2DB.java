package gift.db;

import gift.dto.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductH2DB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initialize() {
        createProductTable();
        addProduct(new Product(1L, "Americano", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        addProduct(new Product(2L, "Latte", 5500, "https://cdn.pixabay.com/photo/2023/07/08/13/17/coffee-8114518_1280.png"));
        addProduct(new Product(3L, "Sandwich", 7700, "https://cdn.pixabay.com/photo/2023/08/12/02/58/sandwich-8184642_1280.png"));
        addProduct(new Product(4L, "Cup Cake", 10000, "https://cdn.pixabay.com/photo/2023/05/31/14/41/ai-generated-8031574_1280.png"));
    }

    private void createProductTable() {
        var sql = """
                create table product (
                  id bigint,
                  name varchar(255),
                  price int,
                  imageUrl varchar(255),
                  primary key (id)
                )
                """;
        jdbcTemplate.execute(sql);
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO product (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
        return rowsAffected > 0;
    }

    public Product getProduct(Long id) {
        String sql = "select * from product where id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
    }

    public List<Product> getProducts() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public void removeProduct(Long id) {
        String sql = "delete from product where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void removeProducts(List<Long> productIds) {
        for (Long productId : productIds) {
            String sql = "delete from product where id = ?";
            jdbcTemplate.update(sql, productId);
        }
    }

    public boolean updateProduct(Product product) {
        String sql = "update product set name=?,price=?,imageUrl=? where id=?";
        int rowsAffected = jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
        return rowsAffected > 0;
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImageUrl(rs.getString("imageUrl"));
            return product;
        }
    }

}
