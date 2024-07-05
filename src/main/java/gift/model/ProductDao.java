package gift.model;

import gift.common.exception.EntityNotFoundException;
import gift.controller.dto.request.ProductRequest;
import gift.controller.dto.response.ProductResponse;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {
    private final JdbcClient jdbcClient;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductDao(JdbcClient jdbcClient, DataSource dataSource) {
        this.jdbcClient = jdbcClient;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }

    public void updateById(long id, ProductRequest request) {
        var sql = "update product set name = ?, price = ?, imageUrl = ? where id = ?";
        jdbcClient.sql(sql)
                .params(request.name(), request.price(), request.imageUrl(), id)
                .update();
    }

    public Long save(ProductRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.name());
        params.put("price", request.price());
        params.put("image_url", request.imageUrl());

        return simpleJdbcInsert.executeAndReturnKey(params).longValue();
    }

    public ProductResponse findById(long id) {
        var sql = "select * from product where id = ?";
        Product product = jdbcClient.sql(sql)
                .params(id)
                .query(Product.class)
                .optional()
                .orElseThrow(() ->
                        new EntityNotFoundException("Product with id " + id + " not found"));
        return ProductResponse.from(product);
    }


    public List<ProductResponse> findAll() {
        var sql = "select * from product";
        List<Product> productList = jdbcClient.sql(sql)
                .query(Product.class)
                .list();
        return productList.stream()
                .map(ProductResponse::from)
                .toList();
    }

    public void deleteById(long id) {
        var sql = "delete from product where id = ?";
        jdbcClient.sql(sql)
                .params(id)
                .update();
    }
}
