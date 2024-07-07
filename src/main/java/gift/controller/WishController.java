package gift.controller;

import gift.dto.WishDto;
import gift.service.WishService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.CacheRequest;
import java.util.Map;

@RequestMapping("/wish")
@Controller
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    //insert
    @PostMapping()
    public ResponseEntity<Map<String,Object>> insert(@RequestBody WishDto.Request request) {
        return wishService.insert(request);
    }

    //getAll

}
