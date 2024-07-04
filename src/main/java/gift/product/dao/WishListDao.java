package gift.product.dao;

import gift.product.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class WishListDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WishListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createWishListTable() {
        System.out.println("[WishListDao] createWishListTable()");
        var sql = """
            create table wish_list (
              id bigint not null,
              productId bigint not null,
              count int not null,
              memberEmail varchar(255) not null,
              primary key (id),
              foreign key (productId) references product_list(id),
              foreign key (memberEmail) references member_list(email)
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public List<Product> getAllProducts(String email) {
        System.out.println("[WishListDao] getAllProducts()");
        var sql = """
                    select p.id, p.name, p.price, p.imageUrl
                    from product_list p
                    join wish_list w on p.id = w.productId
                    where w.memberEmail = ?
                  """;
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("imageUrl")
        ), email);
    }

}
