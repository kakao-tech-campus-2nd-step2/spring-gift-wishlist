package gift.controller;

import gift.domain.MenuRequest;
import gift.service.MenuService;
import gift.domain.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    Pattern signPattern = Pattern.compile("[!@#$%^*,.?\":{}|<>\\\\]");
    Pattern kakaoPattern = Pattern.compile("카카오");
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    private void checkName(String name){
        if(name.length() > 15){
            throw new NoSuchElementException("상품명은 15자 이내로 작성하여야 합니다.");
        }
        else if(signPattern.matcher(name).find()){
            throw new NoSuchElementException(" ( ), [ ], +, -, &, /, _ 이외의 특수문자는 사용이 불가능합니다");
        }
        else if(kakaoPattern.matcher(name).find()){
            throw new NoSuchElementException("\"카카오\"가 포함된 상품명은 담당 MD와 협의해주세요");
        }
    }
    @PostMapping
    public String save(
            @ModelAttribute MenuRequest request
    ) {
        checkName(request.name());
        Menu newMenu = menuService.save(request.name(),request.price(),request.imageUrl());
        return "redirect:/menu";
    }

    @GetMapping
    public String read(Model model){
        List<Menu> menus = menuService.findall();
        model.addAttribute("menus", menus);
        return "Menu";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Menu menu = menuService.findById(id);
        model.addAttribute("menu", menu);
        return "update_menu";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute MenuRequest request,
            Model model
    ){
        checkName(request.name());
        menuService.update(
                id,
                request.name(),
                request.price(),
                request.imageUrl()
        );

        List<Menu> menus = menuService.findall();
        model.addAttribute("menus", menus);
        return "Menu";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        Menu menu = menuService.findById(id);
        menuService.delete(id);
        List<Menu> menus = menuService.findall();
        model.addAttribute("menus", menus);
        return "Menu";
    }
}
