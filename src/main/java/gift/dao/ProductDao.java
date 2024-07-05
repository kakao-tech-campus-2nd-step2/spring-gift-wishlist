package gift.dao;

import gift.model.Product;
import gift.model.ProductName;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.core.io.Resource;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;
    private ResourceLoader resourceLoader;

    public ProductDao(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }
    
    @PostConstruct
    public void createProductTable() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:sql/createProductTable.sql");
        String sql = new BufferedReader(new InputStreamReader(resource.getInputStream()))
                .lines()
                .collect(Collectors.joining("\n"));

        jdbcTemplate.execute(sql);
    }

    public void insertProduct(Product product) {
        var sql = "insert into products (id, productName, price, imageUrl, amount) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(),product.imageUrl(), product.amount());
    }

    public void deleteProduct(long id) {
        var sql = "delete from products where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateProduct(Product product){
        var sql = "update products set productName = ? , price = ?, imageUrl = ?, amount = ? where id = ? ";
        jdbcTemplate.update(sql, product.name(), product.price(),product.imageUrl(), product.amount(), product.id());
    }

    public void purchaseProduct(long id, int amount){
        var sql = "update products set amount = amount - ? where id = ? ";
        jdbcTemplate.update(sql,amount, id);
    }

    public List<Product> selectAllProducts() {
        var sql = "select id, productName, price, imageUrl, amount from products";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        new ProductName(resultSet.getString("productName")),
                        resultSet.getInt("price"),
                        resultSet.getString("imageUrl"),
                        resultSet.getInt("amount")
                )
        );
    }

    public Product selectProduct(long id) {
        var sql = "select id, productName, price, imageUrl, amount from products where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        new ProductName(resultSet.getString("productName")),
                        resultSet.getInt("price"),
                        resultSet.getString("imageUrl"),
                        resultSet.getInt("amount")
                ),
                id
        );
    }

    public boolean isProductInDB(long id) {
        var sql = "select count(*) from products where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count > 0;
    }
}
