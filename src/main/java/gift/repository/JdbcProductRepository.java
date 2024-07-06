package gift.repository;

import gift.Entity.Product;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 안 되면 DataSoruce
    @Autowired
    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean save(@Valid Product product){
        String sql = "INSERT INTO products(id, name, price, imageUrl) VALUES (?,?,?,?)";
        try {
            jdbcTemplate.update(sql, product.id(), product.name(), product.price(), product.imageUrl());
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from products";
        List<Product> products = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Product product = new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getString("imageUrl")
                    );
                    return product;
                });
        return products;
    }

    @Override
    public Optional<Product> findById(long id) {
        String sql = "select * from products where id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (resultSet, rowNum) -> new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getString("imageUrl")
                    )
            );
            return Optional.ofNullable(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Boolean updateById(long id, @Valid Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        try {
            int affectedRows = jdbcTemplate.update(
                    sql,
                    product.name(),
                    product.price(),
                    product.imageUrl(),
                    id
            );
            if (affectedRows > 0) return true;
        }catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Boolean deleteById(long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try {
            int rowsDeleted = jdbcTemplate.update(sql, id);
            if(rowsDeleted > 0) return true;
        } catch (DataAccessException e) {
            return false;
        }
        return false;
    }

    @Description("ROW(record) mapping")
    private RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        int price = rs.getInt(3);
        String imageUrl = rs.getString(4);
        return new Product(id, name, price, imageUrl);
    };

    /*@Override
    public Optional<Void> save(Product product) {
        String sql = "INSERT INTO products(id, name, price, imageUrl) VALUES (?,?,?,?)";
        try {
            jdbcTemplate.update(sql, product.id(), product.name(), product.price(), product.imageUrl());
            return Optional.ofNullable(null);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }*/


    /*public Optional<Void> update(long id, Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        try {
            int affectedRows = jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl(), id);
            if (affectedRows > 0) {
                return Optional.of(product);
            } else {
                return Optional.empty();
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }*/

}
