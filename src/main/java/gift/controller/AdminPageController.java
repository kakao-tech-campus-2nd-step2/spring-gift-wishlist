package gift.controller;

import gift.dto.ProductDTO;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPageController {

    private final ProductService pm;

    public AdminPageController(ProductService pm) {
        this.pm = pm;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("products",pm.readAll());
        model.addAttribute("productDTO",new ProductDTO());
        return "admin/index";//렌더링하는 html 이름
    }

    @PostMapping("/admin") //admin으로 오는 post에 대해서 submit
    public String adminPageSubmit(@ModelAttribute("productDTO") ProductDTO productDTO) {
        pm.create(productDTO); //서비스에 접근해서 해당 부분을 추가해주도록 한다.
        return "redirect:/admin";
    }

    @PutMapping("/admin/{id}")
    public String adminPageUpdate(@PathVariable Long id,@ModelAttribute("productDTO") ProductDTO productDTO) {
        changeCheckAndUpdate(id,productDTO);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String adminPageDelete(@PathVariable Long id) {
        pm.delete(id);
        return "redirect:/admin";
    }

    private void changeCheckAndUpdate(Long id, ProductDTO dto) {

        if (dto.getName().length()>0){
            pm.updateName(id, dto.getName());
        }
        if (dto.getPrice()!=null){
            pm.updatePrice(id, dto.getPrice());
        }
        if (dto.getImageUrl().length()>0){
            pm.updateImageUrl(id, dto.getImageUrl());
        }
    }
}
