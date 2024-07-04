package gift.product.dao;

import gift.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final RestClientAutoConfiguration restClientAutoConfiguration;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate,
        RestClientAutoConfiguration restClientAutoConfiguration) {
        this.jdbcTemplate = jdbcTemplate;
        this.restClientAutoConfiguration = restClientAutoConfiguration;
    }

    public void createProductTable() {
        System.out.println("[ProductDao] createProductTable()");
        var sql = """
            create table product_list (
              id bigint,
              name varchar(255),
              price int,
              imageUrl varchar(255),
              primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public void registerProduct(Product product) {
        System.out.println("[ProductDao] registerProductTable()");
        var sql = "insert into product_list (id, name, price, imageUrl) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public List<Product> searchProduct(String keyword) {
        System.out.println("[ProductDao] searchProduct()");
        var sql = "select id, name, price, imageUrl from product_list where lower(name) like ?";
        String searchKeyword = "%" + keyword.toLowerCase() + "%";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("imageUrl")
        ), searchKeyword);
    }

    public void deleteProduct(long id) {
        System.out.println("[ProductDao] deleteProduct()");
        var sql = "delete from product_list where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateProduct(Product product) {
        System.out.println("[ProductDao] updateProduct()");
        var sql = "update product_list set name = ?, price = ?, imageUrl = ? where id = ?";
        jdbcTemplate.update(
            sql,
            product.getName(),
            product.getPrice(),
            product.getImageUrl(),
            product.getId()
        );
    }

    public List<Product> listupProducts() {
        System.out.println("[ProductDao] listupProducts()");
        var sql = "select id, name, price, imageUrl from product_list";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("imageUrl")
        ));
    }

    public boolean existsById(Long id) {
        String sql = "select count(*) from product_list where id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
