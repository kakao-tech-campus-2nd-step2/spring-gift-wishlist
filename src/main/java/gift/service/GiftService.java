package gift.service;


import gift.model.Gift;
import gift.model.GiftDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftService {

    private final GiftDao giftDao;

    public GiftService(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    public List<Gift> getAllGifts() {
        return giftDao.findAll();
    }

    public Gift getGift(Long id) {
        Gift gift = giftDao.findById(id);
        return gift;
    }


    public void addGift(Gift gift) {
        giftDao.create(gift);
    }

    public void updateGift(Gift gift,Long id) {
        giftDao.updateById(gift,id);
    }

    public void deleteGift(Long id) {
        giftDao.deleteById(id);
    }

}
