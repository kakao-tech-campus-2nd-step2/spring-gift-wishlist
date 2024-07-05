package gift.service;

import gift.domain.WishListRequest;
import gift.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    WishListRepository wishListRepository;
    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void create(WishListRequest wishListRequest){
        wishListRepository.create(wishListRequest);
    }
}