package gift.Service;

import gift.Model.RequestWishListDTO;
import gift.Model.ResponseWishListDTO;
import gift.Model.User;
import gift.Repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<ResponseWishListDTO> getWishList(User user) {
        return wishListRepository.selectWishList(user.getEmail());
    }
}
