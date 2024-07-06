package gift.controller;

import gift.model.Gift;
import gift.model.GiftDao;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/gifts")
public class GiftController {

    private GiftDao giftDao;

    GiftController(GiftDao giftDao){
        this.giftDao = giftDao;
    }

    @PostMapping
    public void addGift(@RequestBody Gift giftReq){
        giftDao.create(giftReq);
    }

    @GetMapping("/{id}")
    public Gift getGift(@PathVariable Long id){
        return giftDao.findById(id);
    }

    @GetMapping
    public Collection<Gift> getAllGift(){
        return giftDao.findAll();
    }

    @PutMapping("/{id}")
    public void updateGift(@PathVariable Long id , @RequestBody Gift giftReq){
         giftDao.updateById(giftReq, id);
    }

    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable Long id){
        giftDao.deleteById(id);
    }
}