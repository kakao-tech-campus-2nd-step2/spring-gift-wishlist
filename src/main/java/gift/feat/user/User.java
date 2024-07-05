package gift.feat.user;

import java.util.ArrayList;
import java.util.List;

import gift.feat.product.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
	private Long id;
	private String email;
	private String password;
	private String role;

	public static User of(Long id, String email, String password, String role) {
		return User.builder()
			.id(id)
			.email(email)
			.password(password)
			.role(role)
			.build();
	}
}
