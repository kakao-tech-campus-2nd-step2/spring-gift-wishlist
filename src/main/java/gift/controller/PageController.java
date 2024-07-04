package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    /*
     * 관리자 페이지 기본 화면 연결을 위한 Controller
     */
    @GetMapping("/")
    public String start() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/register")
    public String register(){
        return "register.html";
    }
}
