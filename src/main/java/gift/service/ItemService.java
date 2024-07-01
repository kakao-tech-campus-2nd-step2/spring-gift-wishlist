package gift.service;

import gift.Model.Item;
import gift.Model.ItemDTO;
import gift.repository.ItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //Item 저장 메서드
    public void insertItem(ItemDTO itemDTO){
       itemRepository.insert(itemDTO);
    }

    //Item 조회 메서드
    public Item findItem(Long id){
       return itemRepository.findById(id);
    }
    //Item 목록 반환 메서드
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
