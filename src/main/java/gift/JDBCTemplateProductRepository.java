package gift;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCTemplateProductRepository implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;
    private final AtomicLong sequence = new AtomicLong();

    public JDBCTemplateProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        String sql = """
            create table product(
               id BIGINT,
               name varchar(255),
               price int,
               imageUrl varchar(255),
               primary key(id) 
            )
            """;
        jdbcTemplate.execute(sql);
    }

    @Override
    public Product insert(Product product) {
        Long id = sequence.getAndIncrement();
        product.setId(id);
        var sql = "insert into product (id, name, price, imageUrl) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
        return product;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from product", productRowMapper());
    }

    @Override
    public Product findById(Long id) {
        var sql = "select * from product where id = ?";
        return jdbcTemplate.queryForObject(
            sql, productRowMapper(), id
        );
    }

    @Override
    public Product update(Product product) {
        String sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
        return product;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM product WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        System.out.println(count);
        return count != null && count > 0;
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            Integer price = rs.getInt("price");
            String imageUrl = rs.getString("imageUrl");
            Product product = new Product(name, price, imageUrl);
            product.setId(id);
            return product;
        };
    }
}
