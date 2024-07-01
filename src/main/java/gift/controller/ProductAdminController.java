package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductAdminController {

    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

}
