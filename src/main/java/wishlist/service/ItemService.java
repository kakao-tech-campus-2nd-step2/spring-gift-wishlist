package wishlist.service;

import wishlist.model.Item;
import wishlist.model.ItemDTO;
import wishlist.repository.ItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void insertItem(ItemDTO itemDTO){
       itemRepository.insert(itemDTO);
    }

    public Item findItem(Long id){
       return itemRepository.findById(id);
    }

    public List<Item> getList(){
        return itemRepository.findAll();
    }

    public void updateItem(ItemDTO itemDTO,Long id){
        itemRepository.update(id,itemDTO);
    }
    public void deleteItem(Long id){
        itemRepository.delete(id);
    }
}
