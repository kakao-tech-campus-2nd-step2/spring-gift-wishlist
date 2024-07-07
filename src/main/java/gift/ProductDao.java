package gift;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    @Autowired
    private JdbcClient jdbcClient;

    public ProductDao(JdbcClient jdbcClient) {this.jdbcClient = jdbcClient;}

    public List<Product> findAllProduct() {
        var sql = """
            select * 
            from product
            """;
          
        List<Product> products = jdbcClient.sql(sql).query(Product.class).list();
        return products;
    }

    public Optional<Product> findProductById(Long id) {
        var sql = """
            select id, name price, url
            from product
            where id = ?
            """;

        return jdbcClient.sql(sql).param(id).query(Product.class).optional();
    }

    public void addProduct(Product product) {
        var sql = """
            insert into product (id, name, price, url)
            values (?,?,?,?)
            """;

        jdbcClient.sql(sql)
            .param(product.getId())
            .param(product.getName())
            .param(product.getPrice())
            .param(product.getUrl())
            .update();
    }

    public Integer updateProductById(Long id, ProductRequestDto productRequestDto) {
        var sql = """
            update product
            set name = ?, price = ?, url = ?
            where id = ?
            """;

        return jdbcClient.sql(sql)
            .param(productRequestDto.name())
            .param(productRequestDto.price())
            .param(productRequestDto.url())
            .param(id)
            .update();
    }
    public void deleteProductById(Long id) {
        var sql = """
            delete from product
            where id = ?
            """;

        jdbcClient.sql(sql)
            .param(id)
            .update();
    }
}
