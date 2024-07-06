package gift.service;

import gift.model.WishList;
import gift.repository.WishlistRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

}
