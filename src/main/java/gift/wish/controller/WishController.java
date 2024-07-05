package gift.wish.controller;

import gift.auth.domain.AuthInfo;
import gift.auth.service.AuthService;
import gift.global.response.ResultCode;
import gift.global.response.ResultResponseDto;
import gift.global.security.Login;
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
    private final AuthService authService;

    public WishController(WishService wishService, AuthService authService) {
        this.wishService = wishService;
        this.authService = authService;
    }

    @GetMapping("")
    public ResponseEntity<ResultResponseDto<List<Wish>>> getAllWishes(@Login AuthInfo authInfo) {
        List<Wish> wishes = wishService.getAllWishesByMember(authService.getMemberById(authInfo.memberId()));
        return ResponseHelper.createResponse(ResultCode.GET_ALL_WISHES_SUCCESS, wishes);
    }
}
