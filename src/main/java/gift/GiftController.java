package gift;

import gift.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GiftController {

    @GetMapping("/product")
    public Product findProductById(@RequestParam(value = "product") String id) {
        final Product product = new Product();
        return product;
    }g
}
