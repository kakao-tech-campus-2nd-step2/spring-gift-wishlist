package gift.controller.wish.dto;

import gift.model.wish.Wish;
import java.util.List;

public class WishResponse {

    public record WishListResponse(
        List<Wish> wishes
    ) {

        public static WishListResponse from(List<Wish> wishes) {
            return new WishListResponse(wishes);
        }
    }
}
