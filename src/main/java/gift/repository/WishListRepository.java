package gift.repository;

import gift.Util.JWTUtil;
import gift.dto.ResponseDTO;
import gift.entity.Product;
import gift.entity.User;
import gift.entity.WishList;
import gift.exception.Exception400;
import gift.exception.Exception404;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean isExistWishList(WishList wishList) {
        String sql = "select count(*) from wishlist where user_id=? and product_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, wishList.getUser_id(), wishList.getProduct_id());
        return count > 0;
    }

    public void save(WishList wishList){
        var sql = "insert into wishlist(user_id,product_id) values(?,?)";
        jdbcTemplate.update(sql,wishList.getUser_id(),wishList.getProduct_id());
    }

    public List<ResponseDTO.wishListProduct> getWishList(int tokenUserId) {
        var sql = "select product.name, product.price, product.imageUrl from wishlist inner join product on wishlist.product_id = product.id where user_id = ?";
        List<ResponseDTO.wishListProduct> wishList = jdbcTemplate.query(sql,new Object[]{tokenUserId},(rs, rowNum) -> new ResponseDTO.wishListProduct(
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
        ));
        return wishList;
    }

    public void deleteWishList(int tokenUserId, int productId) {
        if(!isExistWishList(new WishList(tokenUserId,productId)))
            throw new Exception404("wishList에 포함되어있지않음");
        var sql = "delete from wishList where user_id = ? and product_id = ?";
        jdbcTemplate.update(sql,tokenUserId,productId);
    }
}
