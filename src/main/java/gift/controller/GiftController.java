package gift.controller;

import gift.domain.Gift;
import gift.dto.CreateGiftDto;
import gift.service.GiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gifts")
public class GiftController {
    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public ResponseEntity<Gift> getGift(@RequestParam Long id) {
        Gift gift = giftService.getGift(id);
        return ResponseEntity.ok(gift);
    }

    @PostMapping
    public ResponseEntity<Long> createGift(@RequestBody CreateGiftDto giftDto) {
        return ResponseEntity.ok(giftService.createGift(giftDto));
    }


}
