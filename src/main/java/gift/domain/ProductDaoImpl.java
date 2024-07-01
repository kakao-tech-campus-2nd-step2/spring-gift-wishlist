package gift.domain;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao{
    private final JdbcTemplate jdbcTemplate;

    public ProductDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createProductTable(){
        String sql = """
    CREATE TABLE products(
    id bigint,
    name varchar(255),
    price int,
    imageUrl varchar(255),
    primary key(id)
    )
    """;
        jdbcTemplate.execute(sql);}

    public void save(Product product){
        String sql ="INSERT INTO products (id,name,price,imageUrl) values(?,?,?,?)";
        jdbcTemplate.update(sql,product.getId(),product.getName(),product.getPrice(),product.getImageUrl());
    }

    public List<Product> findAll() {
        String sql = "SELECT id, name, price, imageUrl FROM products";

        //query() 메서드는 결과를 자동으로 List로 반환해줌.
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            return new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            );
        });
    }
    public Product findById(Long id){
        String sql = "SELECT id, name, price, imageUrl FROM products WHERE id=?";
         return jdbcTemplate.queryForObject(sql,(resultSet,rowNum)->
             new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            )
        ,id);
    }
    public void update(Product product,Long id){
        String sql ="UPDATE products SET id=?, name=?, price=?, imageUrl=? WHERE id=?";
        jdbcTemplate.update(sql,
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl(),
            id);
    }
    public void deleteById(Long id){
        String sql = "DELETE FROM products WHERE id=?";
        jdbcTemplate.update(sql,id);

    }

}
