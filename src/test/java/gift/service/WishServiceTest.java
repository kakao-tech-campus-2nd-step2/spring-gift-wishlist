package gift.service;

import gift.domain.Wish;
import gift.repository.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WishServiceTest {

    private WishRepository wishRepository;
    private WishService wishService;

    @BeforeEach
    public void setUp() {
        wishRepository = Mockito.mock(WishRepository.class);
        wishService = new WishService(wishRepository);
    }

    @Test
    public void testAddWish() {
        Long memberId = 1L;
        Long productId = 1L;

        Wish wish = new Wish(memberId, productId);
        wishService.addWish(memberId, productId);

        ArgumentCaptor<Wish> wishArgumentCaptor = ArgumentCaptor.forClass(Wish.class);
        verify(wishRepository, times(1)).save(wishArgumentCaptor.capture());
        assertEquals(wish.getMemberId(), wishArgumentCaptor.getValue().getMemberId());
        assertEquals(wish.getProductId(), wishArgumentCaptor.getValue().getProductId());
    }

    @Test
    public void testGetWishes() {
        Long memberId = 1L;
        Wish wish = new Wish(memberId, 1L);
        when(wishRepository.findByMemberId(memberId)).thenReturn(Collections.singletonList(wish));

        List<Wish> wishes = wishService.getWishes(memberId);
        assertEquals(1, wishes.size());
        assertEquals(wish.getMemberId(), wishes.get(0).getMemberId());
    }

    @Test
    public void testRemoveWish() {
        Long memberId = 1L;
        Long productId = 1L;

        wishService.removeWish(memberId, productId);

        verify(wishRepository, times(1)).deleteByMemberIdAndProductId(memberId, productId);
    }
}
