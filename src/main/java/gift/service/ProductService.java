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

    /*
     * DB에 저장된 모든 Product 객체를 불러와 전달해주는 Logic
     */
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
    /*
     * DB에 저장된 모든 Product의 ID를 불러와 전달해주는 Logic
     */
    public List<Long> findAllId(){
        String sql = "select id from products";
        List<Long> idList = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Long id = resultSet.getLong("id");
                    return id;
                });
        return idList;
    }
    /*
     * 객체를 전달받아 DB에 저장해주는 Logic
     */
    public void createProduct(Product product){
        var sql = "INSERT INTO products(id, name, price, imageUrl) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
        return;
    }
    /*
     * DB에 있는 특정한 ID의 객체를 삭제해주는 Logic
     */
    public void deleteProduct(Long id){
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return;
    }
    /*
     * 현재 DB에 존재하는 Product를 새로운 Product로 대체하는 Logic
     */
    public void updateProduct(Product product, Long id){
        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
        return;
    }
}
