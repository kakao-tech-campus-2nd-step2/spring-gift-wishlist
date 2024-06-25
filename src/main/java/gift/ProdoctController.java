package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdoctController {

    private final Map<Long, Product> producsts = new HashMap<>();

    // 모두 조회
    @GetMapping("/api/products")
    public Map<Long, Product> responseAllProducts(){
        return producsts;
    }



}
