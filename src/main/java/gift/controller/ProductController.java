package gift.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gift.dao.ProductDao;
import gift.domain.Product;
import gift.dto.ProductDto;
import gift.exception.ProductNotFoundException;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/admin")
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }

    @GetMapping()
    public String getProducts(Model model) {
        List<Product> productList = productDao.findAll();
        model.addAttribute("products", productList);
        return "admin_page";
    }

    @GetMapping("/new")
    public String showProductForm(Model model){
        model.addAttribute("product", new Product(0, "", 0, ""));
        return "product_form";
    }

    @PostMapping("/new")
    public String addProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("product", productDto);
            return "product_form";
        }

        productDao.findOne(productDto.getId())
            .orElseThrow(() -> new ProductNotFoundException("Product with id " + productDto.getId() + " not found"));

        productDao.insertProduct(productDto);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        
        productDao.findOne(id)
            .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        
        return "edit_product_form";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,@Valid @ModelAttribute ProductDto productDto, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("product", productDto);
            return "product_form";
        }

        productDao.updateProduct(productDto);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productDao.deleteProduct(id);
        return "redirect:/admin";
    }
}