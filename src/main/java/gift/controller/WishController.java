package gift.controller;

import gift.annotation.LoginMember;
import gift.dto.WishRequestDto;
import gift.dto.WishResponseDto;
import gift.entity.User;
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
    public ResponseEntity<List<WishResponseDto>> getWishes(@LoginMember User user) {
        List<WishResponseDto> wishes = wishService.getWishesByUserId(user.id);
        return new ResponseEntity<>(wishes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WishResponseDto> addWish(@LoginMember User user, @RequestBody WishRequestDto wishRequestDto) {
        WishResponseDto createdWish = wishService.addWish(user.id, wishRequestDto);
        return new ResponseEntity<>(createdWish, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long id) {
        wishService.deleteWish(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
