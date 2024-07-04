package gift.controller;


import gift.Product;
import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    //private final ProductController productController;
    //public AdminController(ProductController productController) {
        //this.productController = productController;
    //}

    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService=productService;
    }

    @GetMapping("/admin")
    public String mainRendering(){
        return "main";
    }

    @GetMapping("/admin/get")
    public String adminGetPage(Model model) {
        productService.getAllProducts();
        model.addAttribute("products", productService.getAllProducts());
        return "get";
        /*List<Product> responseEntity = productService.getAllProducts();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Collection<Product> products = responseEntity.getBody();
            model.addAttribute("products", products);
            return "get";
        } else {
            // Handle error appropriately, e.g., by returning an error page
            return "error";
        }*/
    }

    @GetMapping("/admin/post")
    public String adminAddPage(){
        return "add";
    }

    @PostMapping("/admin/post/submit")
    public String submitPostProduct(@ModelAttribute Product product, Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "get";
    }

    //DELETE
    @GetMapping("/admin/delete")
    public String adminDeletePage(){
        return "delete";
    }

    @PostMapping("/admin/delete/submit")
    public String submitDeleteProduct(@RequestParam("id") Long id, Model model){
        productService.deleteProduct(id);
        model.addAttribute("products", productService.getAllProducts());
        return "get";
    }

    //Update
    @GetMapping("/admin/put")
    public String adminUpdatePage(){
        return "update";
    }

    @PostMapping("/admin/put/submit")
    public String submitUpdateProduct(@ModelAttribute Product product, Model model) {
        productService.updateProduct(product.id(), product);
        model.addAttribute("products", productService.getAllProducts());
        return "get";
    }

}
