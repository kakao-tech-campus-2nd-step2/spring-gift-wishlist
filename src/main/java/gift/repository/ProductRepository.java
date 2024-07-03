package gift.repository;

import gift.DTO.SaveProductDTO;
import gift.entity.Option;
import gift.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAllProduct(){
        var sql = "select * from PRODUCT inner join option on product.id = option.id";
        List<Product> products = jdbcTemplate.query(sql,(rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl"),
                rs.getString("option")
        ));
        return products;
    }

    public void saveProduct(SaveProductDTO saveProductDTO) {
        var sql = "insert into product(id,name,price,imageUrl) values (?,?,?,?)";
        jdbcTemplate.update(sql, saveProductDTO.getId(),saveProductDTO.getName(),saveProductDTO.getPrice(),saveProductDTO.getImageUrl());
    }

    public void saveOption(Option option) {
        var sql = "insert into option(id,option) values(?,?)";
        jdbcTemplate.update(sql, option.getId(), option.getOption());
    }

    public void deleteProductByID(int id) {
        var sql = "delete from product where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteOptionsByID(int id) {
        var sql ="delete from option where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Product> findProductByID(int id) {
        var sql = "select * from PRODUCT inner join option on product.id = option.id where product.id = ?";
        List<Product> products = jdbcTemplate.query(sql,new Object[]{id}, (rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl"),
                rs.getString("option")
        ));
        return products;
    }
    public List<Option> findOptionByID(int id){
        var sql = "select * from option where id = ?";
        List<Option> options = jdbcTemplate.query(sql,new Object[]{id}, (rs, rowNum) -> new Option(
                rs.getInt("id"),
                rs.getString("option")
        ));
        return options;
    }
    public boolean isExistProduct(SaveProductDTO  saveProductDTO){
        var sql = "select * from product where id=?";
        List<SaveProductDTO> product = jdbcTemplate.query(sql,new Object[]{saveProductDTO.getId()}, (rs, rowNum) -> new SaveProductDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
        ));
        return !product.isEmpty();
    }

    public boolean isExistOption(Option saveOptionDTO){
        var sql = "select * from option where id=? and option=?";
        List<Option> opt = jdbcTemplate.query(sql,new Object[]{saveOptionDTO.getId(),saveOptionDTO.getOption()}, (rs, rowNum) -> new Option(
                rs.getInt("id"),
                rs.getString("option")
        ));
        return !opt.isEmpty();
    }
}
