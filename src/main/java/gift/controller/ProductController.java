package gift.controller;

import gift.Product;
import gift.model.ProductModel;
import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "list"; // list.html 파일 보여주기
    }

    @GetMapping("/view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "view"; // view.html 파일 보여주기
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productModel", new ProductModel());
        return "add"; // add.html 파일 보여주기
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("productModel") ProductModel productModel) {
        productService.save(productModel);
        return "redirect:/products/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("productModel", new ProductModel(product.getId(), product.getName(), product.getPrice(), product.getImgUrl()));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute("productModel") ProductModel productModel) {
        productService.update(id, productModel);
        return "redirect:/products/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products/list";
    }
}