package gift.repository;

import gift.dto.CreateProduct;
import gift.dto.EditProduct;
import gift.dto.ProductDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public void createProductTable(){
        var sql = """
                create table product (
                    id bigint,
                    name varchar(255),
                    price bigint,
                    url varchar(255),
                    primary key(id)
                )
                """;
        jdbcTemplate.execute(sql);
    }

    public void insertProduct(CreateProduct.Request request) {
        var sql = "insert into product (id, name, price, url) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql,request.getId(),request.getName(), request.getPrice(), request.getImageUrl());
    }

    public ProductDTO selectProduct(long id) {
        var sql = "select id, name, price, url from product where id= ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new ProductDTO(
                        id,
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")
                ),
                id
        );
    }

    public void updateProduct(long id, EditProduct.Request request){
        var sql = "update product set name= ?, price = ?, url = ? where id=?";
        jdbcTemplate.update(sql,request.getName(),request.getPrice(),request.getImageUrl(),id);
    }

    public void deleteProduct(long id){
        var sql = "delete from product where id=?";
        jdbcTemplate.update(sql,id);
    }
}
