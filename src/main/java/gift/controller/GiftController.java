package gift.controller;

import gift.controller.dto.ProductDTO;
import gift.domain.Product;
import gift.service.GiftService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class GiftController {
    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

//    @GetMapping("/{id}")
//    public String getProduct(@PathVariable Long id, Model model){
//        model.addAttribute("product", giftService.getProduct(id));
//        return "detail";
//    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "add";
    }

    @GetMapping
    public String getAllProduct(Model model){
        model.addAttribute("products", giftService.getAllProduct());
        return "list";
    }

    @PostMapping
    public String postProduct(@ModelAttribute ProductDTO productDTO){
        giftService.postProducts(productDTO);
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO productDTO) {
        giftService.putProducts(productDTO,id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = giftService.getProduct(id);
        model.addAttribute("product", product);
        return "form";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        giftService.deleteProducts(id);
        return "redirect:/products";
    }
}