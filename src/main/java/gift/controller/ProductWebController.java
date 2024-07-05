package gift.controller;

import gift.dto.Product;
import gift.model.ProductRepository;
import gift.exception.ProductNotFoundException;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductWebController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductWebController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // 목록 페이지
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    // 예시 product 입력
    @PostConstruct
    public void init(){
        productRepository.addProduct(new Product("example1",100,"imageUrl1"));
        productRepository.addProduct(new Product("example2",200,"imageUrl2"));
    }

    // id 클릭 시 상품 상세보기
    @GetMapping("/{id}")
    public String findProductById(@PathVariable Long id,Model model){
        try{
            Product product = productRepository.findById(id);
            model.addAttribute("product",product);
            return "product";
        }catch(ProductNotFoundException e){
            return "products";
        }
    }

    // 수정
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model)
    {
        Product product = productRepository.findById(id);
        model.addAttribute("product",product);
        return "editForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute Product product)
    {
        productRepository.updateProduct(id, product);
        return "redirect:/products";
    }

    // 추가
    @GetMapping("/add")
    public String addForm(){
        return "addForm";
    }

    @PostMapping("/add")
    public String addProduct(Product product, RedirectAttributes redirectAttributes){
        Product savedProduct = productRepository.addProduct(product);
        redirectAttributes.addAttribute("id",savedProduct.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/products";
    }

    // 삭제
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id){
        productRepository.deleteProduct(id);
        return "redirect:/products";
    }
}
