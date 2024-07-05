package gift;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
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
        return "admin"; // Thymeleaf 템플릿 이름
    }

    @PostMapping("/post")
    public ResponseEntity<HttpStatus> createProduct(@Valid @ModelAttribute Product newProduct) {
        productRepository.save(newProduct);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateProduct(@Valid @ModelAttribute Product changeProduct) {
        productRepository.update(changeProduct);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);

        return "redirect:/products";
    }

}