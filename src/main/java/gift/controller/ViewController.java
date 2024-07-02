package gift.controller;


import gift.Product;
import gift.ProductRepository;
import gift.ProductDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {
    private final ProductRepository productRepository;
    public ViewController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/")
    public String index(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/create-product")
    public String createPage(){
        return "create";
    }

    @PostMapping("/create-product")
    public String create(@ModelAttribute ProductDto productDto){
        productRepository.save(productDto);

        return "redirect:/";
    }

    @GetMapping("/update-product/{id}")
    public String updatePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("id", id);
        return "update";
    }

    @PostMapping("/update-product/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute ProductDto productDto){
        productRepository.update(id, productDto);
        return "redirect:/";
    }

    @GetMapping("/delete-product/{id}")
    public String delete(@PathVariable("id") Long id){
        productRepository.delete(id);
        return "redirect:/";
    }

}
