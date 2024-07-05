package gift.controller;

import gift.dto.WishRequestDto;
import gift.dto.WishResponseDto;
import gift.service.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishes")
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping
    public ResponseEntity<List<WishResponseDto>> getWishes(@RequestAttribute("userId") Long userId) {
        List<WishResponseDto> wishes = wishService.getWishesByUserId(userId);
        return new ResponseEntity<>(wishes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WishResponseDto> addWish(@RequestAttribute("userId") Long userId, @RequestBody WishRequestDto wishRequestDto) {
        WishResponseDto createdWish = wishService.addWish(userId, wishRequestDto);
        return new ResponseEntity<>(createdWish, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long id) {
        wishService.deleteWish(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
