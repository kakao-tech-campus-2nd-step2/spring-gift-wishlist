package gift.product;

import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Product> findProducts() {
        var sql = """
                select id, name, price, imageUrl
                from product
                """;

        List<Product> products = jdbcClient.sql(sql).query(Product.class).list();
        products.forEach(System.out::println);

        return products;
    }

    public Product findProductById(Long productId) {
        var sql = """
                select id, name, price, imageUrl
                from product
                where id = ?
                """;

        return jdbcClient.sql(sql).param(productId).query(Product.class).single();
    }

    public Long addProduct(ProductReqDto productReqDto) {
        var sql = """
                insert into product (name, price, imageUrl)
                values (?, ?, ?)
                """;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param(productReqDto.name())
                .param(productReqDto.price())
                .param(productReqDto.imageUrl())
                .update(keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Integer updateProduct(Long productId, ProductReqDto productReqDto) {
        var sql = """
                update product
                set name = ?, price = ?, imageUrl = ?
                where id = ?
                """;

        return jdbcClient.sql(sql)
                .param(productReqDto.name())
                .param(productReqDto.price())
                .param(productReqDto.imageUrl())
                .param(productId)
                .update();
    }

    public Integer deleteProduct(Long productId) {
        var sql = """
                delete from product
                where id = ?
                """;

        return jdbcClient.sql(sql)
                .param(productId)
                .update();
    }
}
