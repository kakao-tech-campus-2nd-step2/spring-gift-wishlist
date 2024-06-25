package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GiftController {

    @GetMapping("/product")
    public   findProductById(@RequestParam(value = "product") String id) {
        var greeting = new Greeting(1L, name);
        return greeting;
    }
}
