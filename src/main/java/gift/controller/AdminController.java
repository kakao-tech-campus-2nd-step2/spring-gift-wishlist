package gift.controller;


import gift.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

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
    public String adminGetPage(Model model) {
        ResponseEntity<Collection<Product>> responseEntity = productController.getProducts();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Collection<Product> products = responseEntity.getBody();
            model.addAttribute("products", products);
            return "get";
        } else {
            // Handle error appropriately, e.g., by returning an error page
            return "error";
        }
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
