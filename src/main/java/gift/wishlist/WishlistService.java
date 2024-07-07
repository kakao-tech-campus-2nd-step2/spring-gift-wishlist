package gift.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class WishlistService {
    @Autowired
    private final WishlistDAO wishlistDAO;
    public WishlistService(WishlistDAO wishlistDAO) {
        this.wishlistDAO = wishlistDAO;
        wishlistDAO.create();
    }

    public List<WishilistItem> getAllWishlist(String token) {
        try {
            return wishlistDAO.selectAll(token);
        } catch(Exception e) {
            return null;
        }
    }

    public void deleteItem(String token, long id) {
        if(isItem(token, id)) {
            wishlistDAO.delete(token, id);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public void changeNum(String token, long id, long num) {
        if (num == 0) {
            deleteItem(token, id);
        } else {
            wishlistDAO.updateNum(token, id, num);
        }
    }

    public void addItem(String token, long id) {
        if (isItem(token, id)) {
            wishlistDAO.insert(token, id);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public boolean isItem(String token, long id) {
        return wishlistDAO.selectNumByItem(token, id) > 0;
    }
}
