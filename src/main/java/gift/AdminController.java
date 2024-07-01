package gift;


import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AdminController {

    private final ProductController productController;

    public AdminController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping("/admin")
    public String mainRendering(){
        return "main";
    }

    //GET
    @GetMapping("/admin/get")
    public String adminGetPage(Model model){
        Collection<Product> products = productController.getProducts();
        model.addAttribute("products", products);
        return "get";
    }

    //ADD
    @GetMapping("/admin/post")
    public String adminAddPage(){
        return "add";
    }

    @PostMapping("/admin/post/submit")
    public String submitPostProduct(@ModelAttribute Product product, Model model) {
        productController.addProduct(product);
        model.addAttribute("products", productController.getProducts());
        return "get";
    }

    //DELETE
    @GetMapping("/admin/delete")
    public String adminDeletePage(){
        return "delete";
    }

    @PostMapping("/admin/delete/submit")
    public String submitDeleteProduct(@RequestParam("id") Long id, Model model){
        productController.deleteProduct(id);
        model.addAttribute("products", productController.getProducts());
        return "get";
    }

    //Update
    @GetMapping("/admin/put")
    public String adminUpdatePage(){
        return "update";
    }

    @PostMapping("/admin/put/submit")
    public String submitUpdateProduct(@ModelAttribute Product product, Model model) {
        productController.updateProduct(product.id(), product);
        model.addAttribute("products", productController.getProducts());
        return "get";
    }

}
