package gift.controller;

import gift.domain.MenuRequest;
import gift.domain.MenuResponse;
import gift.service.MenuService;
import gift.domain.Menu;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import org.springframework.ui.Model;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public String save(
            @ModelAttribute @Valid MenuRequest request,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldError().getDefaultMessage());
            model.addAttribute("menus", menuService.findall());
            return "Menu"; // 현재 폼 페이지로 돌아감
        }
        menuService.save(request);
        return "redirect:/menu";
    }

    @GetMapping
    public String read(Model model) {
        List<MenuResponse> menus = menuService.findall();
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
            @Valid @ModelAttribute MenuRequest request,
            Model model
    ) {
        menuService.update(
                id,
                request
        );

        List<MenuResponse> menus = menuService.findall();
        model.addAttribute("menus", menus);
        return "Menu";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        Menu menu = menuService.findById(id);
        menuService.delete(id);
        List<MenuResponse> menus = menuService.findall();
        model.addAttribute("menus", menus);
        return "Menu";
    }
}