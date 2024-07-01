package gift.model;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.yml")
class GiftDaoTest {

    @Autowired
    private GiftDao giftDao;

    @BeforeEach
    public void setup() {
        List<Gift> allGifts = giftDao.findAll();
        for (Gift gift : allGifts) {
            giftDao.deleteById(gift.getId());
        }
    }

    @Test
    @DisplayName("create 검사")
    public void testCreate() {
        Gift gift = new Gift(5L, "Book", 20, "book.jpg");
        giftDao.create(gift);

        Gift retrievedGift = giftDao.findById(gift.getId());

        assertNotNull(retrievedGift);
        assertEquals("Book", retrievedGift.getName());
        assertEquals(20, retrievedGift.getPrice());
        assertEquals("book.jpg", retrievedGift.getImageUrl());
    }
    @Test
    @DisplayName("update 검사")
    public void testUpdate(){
        Gift gift = new Gift(5L, "Book", 20, "book.jpg");
        giftDao.create(gift);

        gift.setPrice(4000);
        giftDao.updateById(gift,gift.getId());

        Gift retrievedGift = giftDao.findById(gift.getId());
        assertNotNull(retrievedGift);
        assertEquals("Book", retrievedGift.getName());
        assertEquals(4000, retrievedGift.getPrice());
        assertEquals("book.jpg", retrievedGift.getImageUrl());

    }
    @Test
    @DisplayName("delete 검사")
    public void testDelete(){
        Gift gift = new Gift(5L, "Book", 20, "book.jpg");
        giftDao.create(gift);

        giftDao.deleteById(gift.getId());

        assertNull(giftDao.findById(gift.getId()));
    }

}
