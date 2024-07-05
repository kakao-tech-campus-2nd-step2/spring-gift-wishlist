package gift.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import gift.exception.InvalidProductException;
import gift.model.User;
import gift.model.Wishlist;

@Service
public class WishlistService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Wishlist> getWishlist(User user) {
        String sql = "SELECT w.id, p.name as productName, w.quantity FROM wishlist w JOIN products p ON w.product_id = p.id WHERE w.user_id = ?";
        
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, user.getId());
            }
        };
        
        RowMapper<Wishlist> rowMapper = new RowMapper<Wishlist>() {
            @Override
            public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Wishlist wishlist = new Wishlist();
            	wishlist.setId(rs.getLong("id"));
            	wishlist.setProductName(rs.getString("productName"));
            	wishlist.setQuantity(rs.getInt("quantity"));
                return wishlist;
            }
        };

        return jdbcTemplate.query(sql, pss, rowMapper);
    }
	
	public int addWishlist(User user,String productName, int quantity) {
		String checkProductSql = "SELECT COUNT(*) FROM products WHERE name = ?";
		Integer count = jdbcTemplate.queryForObject(checkProductSql, new Object[]{productName}, Integer.class);
		if (count==null || count==0) {
			throw new InvalidProductException("The product does not exist.");
		}
		String sql = "INSERT INTO wishlist (user_id, product_id, quantity) VALUES (?, (SELECT id FROM products WHERE name = ?), ?)";
		return jdbcTemplate.update(sql, user.getId(), productName, quantity);
	}
	
	public int removeWishlist(User user, String productName) {
		String sql = "DELETE FROM wishlist WHERE user_id = ? AND product_id = (SELECT id FROM products WHERE name = ?)";
		return jdbcTemplate.update(sql, user.getId(), productName);
	}
	
	public int updateWishlistQuantity(User user, String productName, int quantity) {
		if(quantity == 0) {
			return removeWishlist(user, productName);
		}
		String sql = "UPDATE wishlist SET quantity = ? WHERE user_id = ? AND product_id = (SELECT id FROM products WHERE name =?)";
		return jdbcTemplate.update(sql, quantity, user.getId(), productName);
	}
}
