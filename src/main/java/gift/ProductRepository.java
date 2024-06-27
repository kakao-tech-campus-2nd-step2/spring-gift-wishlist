package gift;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createProductTable();
    }

    private void createProductTable() {
        jdbcTemplate.execute("""
                    CREATE TABLE products(
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        price INT,
                        imageUrl VARCHAR(255))
                """);
    }

    // public Product addProduct(String name, Integer price, String imageUrl) {
    // productsMap.put(idGenerator, new Product(idGenerator, name, price,
    // imageUrl));
    // return productsMap.get(idGenerator++);
    // }

    // public List<Product> getAllProduct() {
    // return productsMap.values().stream().toList();
    // }

    // public Product updateProduct(Long id, String name, Integer price, String
    // imageUrl) {
    // return productsMap.put(id, new Product(id, name, price, imageUrl));
    // }

    // public Product deleteProduct(Long id) {
    // return productsMap.remove(id);
    // }
}
