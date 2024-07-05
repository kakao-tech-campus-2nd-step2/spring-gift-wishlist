package gift.controller.product;

import gift.auth.Auth;
import gift.model.user.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductPageController {

    @Auth(role = Role.ADMIN)
    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

}
