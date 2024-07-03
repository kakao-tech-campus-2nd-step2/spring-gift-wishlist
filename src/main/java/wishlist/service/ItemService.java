package wishlist.service;

import wishlist.model.Item;
import wishlist.model.ItemDTO;
import wishlist.model.ItemForm;
import wishlist.repository.ItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void insertItem(ItemForm form) {
        Item item = new Item(form.getName(), form.getPrice(), form.getImgUrl());
        itemRepository.insert(item);
    }

    public ItemDTO findItem(Long id) {
        Item item = itemRepository.findById(id);
        return new ItemDTO(item.getId(), item.getName(), item.getPrice(), item.getImgUrl());
    }

    public List<Item> getList() {
        return itemRepository.findAll();
    }

    public void updateItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.id(), itemDTO.name(), itemDTO.price(), itemDTO.imgUrl());
        itemRepository.update(item);
    }

    public void deleteItem(Long id) {
        itemRepository.delete(id);
    }
}
