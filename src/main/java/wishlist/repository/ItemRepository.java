package wishlist.repository;

import wishlist.model.Item;
import wishlist.model.ItemDTO;
import java.util.List;

public interface ItemRepository {
    Long insert(Item item);
    Item findById(Long id);
    List<Item> findAll();
    void update(Item item);
    void delete(Long id);
}
