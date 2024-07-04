package gift.product.repository;

import gift.product.Product;
import gift.product.dto.ProductReqDto;
import gift.product.exception.ProductCreateException;
import gift.product.exception.ProductDeleteException;
import gift.product.exception.ProductNotFoundException;
import gift.product.exception.ProductUpdateException;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.JdbcClient.MappedQuerySpec;
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

        return jdbcClient.sql(sql)
                .query(Product.class)
                .list();
    }

    public Product findProductById(Long productId) {
        var sql = """
                select id, name, price, imageUrl
                from product
                where id = ?
                """;

        MappedQuerySpec<Product> productQuery = jdbcClient.sql(sql).
                param(productId)
                .query(Product.class);

        // 상품이 없을 경우 예외 발생
        return productQuery.optional().orElseThrow(
                () -> ProductNotFoundException.EXCEPTION);
    }

    public Long addProduct(ProductReqDto productReqDto) {
        var sql = """
                insert into product (name, price, imageUrl)
                values (?, ?, ?)
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcClient.sql(sql)
                    .param(productReqDto.name())
                    .param(productReqDto.price())
                    .param(productReqDto.imageUrl())
                    .update(keyHolder);
        } catch (Exception e) {
            throw ProductCreateException.EXCEPTION;
        }

        return keyHolder.getKey().longValue();
    }

    public void updateProductById(Long productId, ProductReqDto productReqDto) {
        var sql = """
                update product
                set name = ?, price = ?, imageUrl = ?
                where id = ?
                """;

        try {
            jdbcClient.sql(sql)
                    .param(productReqDto.name())
                    .param(productReqDto.price())
                    .param(productReqDto.imageUrl())
                    .param(productId)
                    .update();
        } catch (Exception e) {
            throw ProductUpdateException.EXCEPTION;
        }
    }

    public void deleteProductById(Long productId) {
        findProductById(productId); // 상품이 없을 경우 예외 발생

        var sql = """
                delete from product
                where id = ?
                """;

        try {
            jdbcClient.sql(sql)
                    .param(productId)
                    .update();
        } catch (Exception e) {
            throw ProductDeleteException.EXCEPTION;
        }
    }
}
