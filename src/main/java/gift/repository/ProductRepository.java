package gift.repository;

import gift.DTO.Product;
import gift.domain.ProductEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<ProductEntity> findAll(){
        String sql = "select id, name, price, imageUrl from products";
        List<ProductEntity> productList = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    ProductEntity productEntity = new ProductEntity(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getString("imageUrl")
                    );
                    return productEntity;
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

    public void save(ProductEntity product){
        var sql = "INSERT INTO products(id, name, price, imageUrl) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public void delete(Long id){
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(ProductEntity product, Long id){
        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }
}
