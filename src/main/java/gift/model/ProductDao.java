package gift.model;

import gift.controller.dto.ProductRequest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {
    private final JdbcClient jdbcClient;

    public ProductDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void updateById(long id, ProductRequest request) {
        var sql = "update product set name = ?, price = ?, imageUrl = ? where id = ?";
        jdbcClient.sql(sql)
                .params(request.name(), request.price(), request.imageUrl(), id)
                .update();
    }

    public void save(ProductRequest request) {
        var sql = "insert into product(name, price, imageUrl) values(?, ?, ?)";
        jdbcClient.sql(sql)
                .params(request.name(), request.price(), request.imageUrl())
                .update();

    }

    public Product findById(long id) {
        var sql = "select * from product where id = ?";
        return jdbcClient.sql(sql)
                .params(id)
                .query(Product.class)
                .single();
    }


    public List<Product> findAll() {
        var sql = "select * from product";
        return jdbcClient.sql(sql)
                .query(Product.class)
                .list();
    }

    public void deleteById(long id) {
        var sql = "delete from product where id = ?";
        jdbcClient.sql(sql)
                .params(id)
                .update();
    }
}
