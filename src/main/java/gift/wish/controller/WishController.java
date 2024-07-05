package gift.wish.controller;

import gift.global.response.ResultCode;
import gift.global.response.ResultResponseDto;
import gift.global.utils.ResponseHelper;
import gift.wish.domain.Wish;
import gift.wish.service.WishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wishes")
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("")
    public ResponseEntity<ResultResponseDto<List<Wish>>> getAllWishes() {
        List<Wish> wishes = wishService.getAllWishes();
        return ResponseHelper.createResponse(ResultCode.GET_ALL_WISHES_SUCCESS, wishes);
    }
}
