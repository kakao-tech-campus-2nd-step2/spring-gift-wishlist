package gift.controller;

import gift.dto.WishDto;
import gift.service.WishService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.CacheRequest;
import java.util.Map;

@Controller
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    //insert
    public ResponseEntity<Map<String,Object>> insert(@RequestBody WishDto.Request request) {
        return wishService.insert(request);
    }
}
