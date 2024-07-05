package gift.Service;

import gift.Model.Member;
import gift.Model.Product;
import gift.Repository.MemberRepository;
import gift.Repository.WishlistRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    public List<Product> getAllWishlist() {
        return wishlistRepository.findAllWishlist();
    }

    public void addWishlist(Product product){
        wishlistRepository.addWishlistFromProduct(product);
    }

    public void deleteWishlist(Long id){
        wishlistRepository.deleteWishlistById(id);
    }
}
