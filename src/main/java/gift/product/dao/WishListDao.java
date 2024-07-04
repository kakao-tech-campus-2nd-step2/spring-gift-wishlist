package gift.product.dao;

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
        System.out.println("[ProductDao] createWishListTable()");
        var sql = """
            create table wish_list (
              id bigint not null,
              productId bigint not null,
              count int not null,
              memberEmail varchar(255) not null,
              primary key (id),
              foreign key (productId) references product(id),
            foreign key (memberEmail) references memberEmail(email)
            )
            """;
        jdbcTemplate.execute(sql);
    }

}
