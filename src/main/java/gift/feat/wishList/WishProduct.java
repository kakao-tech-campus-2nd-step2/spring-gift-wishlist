package gift.feat.wishList;

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
public class WishProduct {
	private Long userId;
	private Long productId;

	public static WishProduct of(Long userId, Long productId) {
		return new WishProduct(userId, productId);
	}
}

