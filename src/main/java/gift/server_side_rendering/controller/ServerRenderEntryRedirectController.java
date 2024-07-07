package gift.server_side_rendering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerRenderEntryRedirectController {

    @GetMapping("/")
    public String index() {
        return "redirect:/products";
    }
}
