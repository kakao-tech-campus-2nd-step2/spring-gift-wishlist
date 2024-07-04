package gift.controller;

import gift.dto.ProductDTO;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/products")
@Controller
public class AdminPageController {

    private final ProductService productService;

    public AdminPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("products", productService.readAll());
        model.addAttribute("productDTO", new ProductDTO());
        return "admin/index";//렌더링하는 html 이름
    }

    @PostMapping //admin으로 오는 post에 대해서 submit
    public String adminPageSubmit(@ModelAttribute("productDTO") @Valid ProductDTO productDTO) {
        productService.create(productDTO); //서비스에 접근해서 해당 부분을 추가해주도록 한다.
        return "redirect:/admin/products";
    }

    @PutMapping("/{id}")
    public String adminPageUpdate(@PathVariable Long id,
        @ModelAttribute("productDTO") @Valid ProductDTO productDTO) {
        changeCheckAndUpdate(id, productDTO);
        return "redirect:/admin/products";
    }

    @DeleteMapping("/{id}")
    public String adminPageDelete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    private void changeCheckAndUpdate(Long id, ProductDTO dto) {

        if (dto.getName().length() > 0) {
            productService.updateName(id, dto.getName());
        }
        if (dto.getPrice() != null) {
            productService.updatePrice(id, dto.getPrice());
        }
        if (dto.getImageUrl().length() > 0) {
            productService.updateImageUrl(id, dto.getImageUrl());
        }
    }
}
