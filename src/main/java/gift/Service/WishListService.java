package gift.Service;

import gift.Model.RequestWishListDTO;
import gift.Model.User;
import gift.Repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository){
        this.wishListRepository = wishListRepository;
        wishListRepository.createWishListTable();
    }

    public void addWishList(User user, RequestWishListDTO requestWishListDTO) {
        wishListRepository.insertWishList(user.getEmail(), requestWishListDTO);
    }
}
