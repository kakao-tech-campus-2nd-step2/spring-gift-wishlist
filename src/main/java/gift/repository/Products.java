package gift.repository;

import gift.domain.Product;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Products {
    private final JdbcTemplate jdbcTemplate;

    public Products(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean addProduct(Product product) {
        String sql = "insert into products values(?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql,product.getId(), product.getName(),product.getPrice(),product.getImageUrl());
        return result==1;
    }

    public boolean deleteProduct(Long id){
        String sql="delete from products where id=?";
        int result = jdbcTemplate.update(sql,id);
        return result==1;
    }

    public boolean updateProduct(Product product) {
        String sql = "update products set id=?, name = ?, price = ?, imageUrl = ? where id = ?";
        int result=jdbcTemplate.update(sql,product.getId(),product.getName(),product.getPrice(),product.getImageUrl(),product.getId());
        return result==1;
    }

    public List<Product> findAll() {
        String sql = "select * from products";
        return jdbcTemplate.query(sql, productRowMapper());
    }
    private RowMapper<Product> productRowMapper(){
        return ((((rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImageUrl(rs.getString("imageUrl"));
            return product;
        })));
    }

    public Product getProduct(Long id){
        String sql="select * from products where id=?";
        return jdbcTemplate.queryForObject(sql,productRowMapper(),id);
    }

}