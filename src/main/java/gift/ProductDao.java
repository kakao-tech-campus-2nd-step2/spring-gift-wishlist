package gift;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostConstruct
    public void createProductTable() {
        var sql = """
            create table products (
              id bigint,
              productName varchar(255),
              price int,
              imageUrl varchar(255),
              amount int,
              primary key (id)
            )
            """;
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
                        resultSet.getString("productName"),
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
                        resultSet.getString("productName"),
                        resultSet.getInt("price"),
                        resultSet.getString("imageUrl"),
                        resultSet.getInt("amount")
                ),
                id
        );
    }
    public boolean checkProduct(long id) {
        var sql = "select count(*) from products where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count > 0;
    }
}
