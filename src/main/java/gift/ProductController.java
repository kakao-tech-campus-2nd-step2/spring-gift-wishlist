package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("newProduct", new Product()); // 새 상품 객체
        model.addAttribute("product", new Product()); // 편집을 위한 빈 객체*/
        return "home"; // Thymeleaf 템플릿 이름
    }

    @PostMapping("/post")
    public String createProduct(@ModelAttribute Product newProduct){
        productRepository.save(newProduct);
        return "redirect:/products";
    }

    @PostMapping ("/update")
    public String updateProduct(@ModelAttribute Product changeProduct){
        productRepository.update(changeProduct);

        return "redirect:/products";
    }

    @GetMapping ("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);

        return "redirect:/products";
    }

}