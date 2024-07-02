package gift.repository;

import org.springframework.jdbc.core.RowMapper;
import gift.domain.Product;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		Long price = rs.getLong("price");
		String imageUrl = rs.getString("imageUrl");
		return Product.of(id, name, price, imageUrl);
	}
}
