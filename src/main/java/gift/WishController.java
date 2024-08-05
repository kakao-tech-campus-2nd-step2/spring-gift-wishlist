package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
public class WishController {

    @Autowired
    private WishService wishService;

    @GetMapping
    public List<Wish> getWishes(@LoginMember Member member) {
        return wishService.getWishesByMemberId(member.getId());
    }

    @PostMapping
    public void addWish(@RequestBody WishRequest request, @LoginMember Member member) {
        wishService.addWish(member.getId(), request.getProductId());
    }

    @DeleteMapping("/{productId}")
    public void deleteWish(@PathVariable Long productId, @LoginMember Member member) {
        wishService.deleteWish(member.getId(), productId);
    }
}
