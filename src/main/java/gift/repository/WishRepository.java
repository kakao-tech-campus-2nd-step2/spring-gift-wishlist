package gift.repository;

import gift.entity.Wish;

import java.util.List;

public interface WishRepository {
    public void addToWishlist(String email, String type, long productId);
    public void removeFromWishlist(String email, String type, long productId);
    public List<Wish> getWishlistItems(String email);

}
