package gift.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductWithQuantityDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductWithQuantityDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @PostConstruct
    public void createProductWithQuantityTable(){
        String sql = """
            CREATE TABLE ProductWithQuantity(
            id bigint AUTO_INCREMENT,
            product_id bigint,
            quantity int,
            wishlist_id bigint,
            PRIMARY KEY(id),
            FOREIGN KEY(wishlist_id) REFERENCES Wishlist(id)
            );
            """;
        jdbcTemplate.execute(sql);
    }

    public void saveProductWithQuantity(long productId, int quantity,long wishlistId){
        String sql="INSERT INTO ProductWithQuantity(product_id,quantity,wishlist_id) values(?,?,?)";
        jdbcTemplate.update(sql,productId,quantity,wishlistId);
    }

}
