package gift.controller;

import gift.domain.MenuRequest;
import gift.service.MenuService;
import gift.domain.Menu;

import jakarta.validation.Valid;


import java.util.List;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @PostMapping
    public String save(

            @ModelAttribute @Valid MenuRequest request


    ) {
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

            @Valid @ModelAttribute MenuRequest request,

            Model model
    ){
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
