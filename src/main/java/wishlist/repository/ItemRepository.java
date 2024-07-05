package wishlist.repository;

import java.util.List;
import wishlist.model.item.Item;

public interface ItemRepository {

    Long insert(Item item);

    Item findById(Long id);

    List<Item> findAll();

    void update(Item item);

    void delete(Long id);
}
