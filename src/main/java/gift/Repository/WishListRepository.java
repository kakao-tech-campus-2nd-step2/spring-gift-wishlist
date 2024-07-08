package gift.Repository;

import gift.Model.RequestWishListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {
    private final JdbcTemplate jdbctemplate;

    @Autowired
    public WishListRepository(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    public void createWishListTable() {
        var sql = """
                create table WishLists(
                id bigint auto_increment,
                email varchar(50),
                productId bigint,
                count int,
                primary key(id)
                )
                """;
        jdbctemplate.execute(sql);
    }

    public void insertWishList(String email, RequestWishListDTO requestWishListDTO) {
        var sql = "insert into WishLists (email, productId, count) values (?,?,?)";
        jdbctemplate.update(sql, email, requestWishListDTO.getProductId(), requestWishListDTO.getCount());
        System.out.println("성공적으로 삽입 완료");
    }


}
