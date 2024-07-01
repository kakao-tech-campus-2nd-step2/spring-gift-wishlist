package gift.controller;

import gift.domain.Product;
import gift.repository.Products;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class AdminController {
    private final Products products;
    //생성자 주입 권장
    public AdminController(Products products) {
        this.products=products;
    }
    //전체 상품목록
    @GetMapping
    public String viewProducts(Model model) {
        model.addAttribute("products", products.findAll());
        return "products-list";
    }
    //상품 추가폼 끌어오기
    @GetMapping("/add")
    public String addProductsForm(Model model) {
        model.addAttribute("product",new Product());
        return "addProducts-form";
    }
    //상품추가 Post
    @PostMapping("/add")
    public String addProduct(Model model,@ModelAttribute Product product){
        if(products.addProduct(product)){
            return "redirect:/products";
        }
        model.addAttribute("error","이미존재하는 상품 id");
        return "addProducts-form";
    }

    //상품업데이트
    @GetMapping("/update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        Product product=products.getProduct(id);
        if(product!=null){
            model.addAttribute("product",product);
            return "updateProducts-form";
        }
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model, @ModelAttribute Product product){
        if(products.updateProduct(product)){
            return "redirect:/products";
        }
        model.addAttribute("error","존재하지 않는 상품 id");
        return "updateProducts-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        products.deleteProduct(id);
        return "redirect:/products";
    }
}
