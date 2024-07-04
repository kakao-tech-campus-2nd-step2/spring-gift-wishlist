package gift.api.product;

import java.sql.Types;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    private final JdbcClient jdbcClient;

    public ProductDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Product> getAllProducts() {
        return jdbcClient.sql("select * from product")
                    .query(Product.class)
                    .list();
    }

    public Long insert(ProductDto productDto) {
        jdbcClient.sql("insert into product (name, price, imageUrl) values (:name, :price, :imageUrl)")
            .param("name", productDto.getName(), Types.VARCHAR)
            .param("price", productDto.getPrice(), Types.INTEGER)
            .param("imageUrl", productDto.getImageUrl(), Types.VARCHAR)
            .update();
        return jdbcClient.sql("select id from product where name = :name")
            .param("name", productDto.getName(), Types.VARCHAR)
            .query(Product.class)
            .single()
            .getId();
    }

    public void update(long id, ProductDto productDto) {
        jdbcClient.sql("update product set name = :name, price = :price, imageUrl = :imageUrl where id = :id")
            .param("name", productDto.getName(), Types.VARCHAR)
            .param("price", productDto.getPrice(), Types.INTEGER)
            .param("imageUrl", productDto.getImageUrl(), Types.VARCHAR)
            .param("id", id, Types.BIGINT)
            .update();
    }

    public void delete(long id) {
        jdbcClient.sql("delete from product where id = :id")
            .param("id", id)
            .update();
    }
}
