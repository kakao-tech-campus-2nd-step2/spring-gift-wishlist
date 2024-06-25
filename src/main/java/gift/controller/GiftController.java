package gift.controller;

import gift.domain.Gift;
import gift.dto.CreateGiftDto;
import gift.dto.GiftDto;
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
    public ResponseEntity<GiftDto> getGift(@RequestParam Long id) {
        GiftDto giftDto = giftService.getGift(id);
        return ResponseEntity.ok(giftDto);

    }

    @PostMapping
    public ResponseEntity<Long> createGift(@RequestBody CreateGiftDto giftDto) {
        Long createdId = giftService.createGift(giftDto);
        return ResponseEntity.ok(createdId);
    }


}
