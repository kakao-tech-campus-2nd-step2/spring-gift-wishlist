package gift;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createProductTable(){
        String sql = """
            create table product(
               id BIGINT AUTO_INCREMENT,
               name varchar(255),
               price int,
               imageUrl varchar(255),
               primary key(id) 
            )
            """;
        jdbcTemplate.execute(sql);

    }
    public void save(ProductDto productDto) {
        String sql = "INSERT INTO product (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, productDto.getName(), productDto.getPrice(), productDto.getImageUrl());
    }
    public Product findById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, productRowMapper());
    }

    public Product findByName(String name) {
        String sql = "SELECT * FROM product WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, productRowMapper());
    }

    public List<Product> findAll(){
        return jdbcTemplate.query("select * from product", productRowMapper());
    }
    public void update(Long id, ProductDto productDto){
        String sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, productDto.getName(), productDto.getPrice(), productDto.getImageUrl(), id);
    }
    public void delete(Long id){
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImageUrl(rs.getString("imageUrl"));
            return product;
        };
    }
}
