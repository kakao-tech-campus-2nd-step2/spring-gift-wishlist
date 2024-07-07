package gift.controller;

import gift.domain.Product;
import gift.exception.ProductNotFoundException;
import gift.service.ExternalProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/external/products")
public class ExternalProductWebController {

    private final ExternalProductService externalProductService;

    @Autowired
    public ExternalProductWebController(ExternalProductService externalProductService){
        this.externalProductService = externalProductService;
    }

    // 목록 페이지
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = externalProductService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    // id 클릭 시 상품 상세보기
    @GetMapping("/{id}")
    public String findProductById(@PathVariable Long id,Model model){
        try{
            Product product = externalProductService.findById(id);
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
        Product product = externalProductService.findById(id);
        model.addAttribute("product",product);
        return "editForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,@Valid @ModelAttribute Product product, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "editForm";
        }
        externalProductService.updateProduct(id, product);
        return "redirect:/external/products";
    }

    // 추가
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("product",new Product());
        return "addForm";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addForm";
        }
        Product savedProduct = externalProductService.addProduct(product);
        return "redirect:/external/products";
    }

    // 삭제
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id){
        externalProductService.deleteProduct(id);
        return "redirect:/external/products";
    }
}
