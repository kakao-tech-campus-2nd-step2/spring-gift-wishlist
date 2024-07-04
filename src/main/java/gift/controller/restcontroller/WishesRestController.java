package gift.controller.restcontroller;

import gift.common.annotation.LoginMember;
import gift.controller.dto.request.WishRequest;
import gift.controller.dto.response.WishResponse;
import gift.service.WishService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishes")
public class WishesRestController {
    private final WishService wishService;

    public WishesRestController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createWish(@Valid @RequestBody WishRequest request,
                                           @LoginMember Long memberId) {
        wishService.save(request.productId(), memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
