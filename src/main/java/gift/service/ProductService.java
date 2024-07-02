package gift.service;

import gift.DTO.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final JdbcTemplate jdbcTemplate;
    public ProductService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll(){
        String sql = "select id, name, price, imageUrl from products";
        List<Product> productList = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Product product = new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getString("imageUrl")
                    );
                    return product;
                });
        return productList;
    }
    public List<Long> findAllId(){
        String sql = "select id from products";
        List<Long> idList = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Long id = resultSet.getLong("id");
                    return id;
                });
        return idList;
    }
    public void createProduct(Product product){
        var sql = "INSERT INTO products(id, name, price, imageUrl) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
        return;
    }
    public void deleteProduct(Long id){
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return;
    }
    public void updateProduct(Product product, Long id){
        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
        return;
    }
}
