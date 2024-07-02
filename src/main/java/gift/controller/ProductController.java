package gift.controller;

import gift.entity.Product;
import gift.service.ProductService;
import org.hibernate.validator.internal.constraintvalidators.bv.NotBlankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import Exception.*;
import java.util.List;


@RestController
public class  ProductController {
    //private Map<Long, Product> products = new HashMap<>();
    //private static Long count = 1L;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProductService productService;

    @GetMapping("/admin/products")
    public ModelAndView adminProducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return new ModelAndView("admin/products");
    }

    @GetMapping("/admin/add")
    public ModelAndView adminProductsAdd(Model model){
        return new ModelAndView("admin/add");
    }

    @GetMapping("/admin/modify")
    public ModelAndView adminProductsModify(Model model){
        return new ModelAndView("admin/modify");
    }

    @GetMapping("/admin/delete")
    public ModelAndView adminProductsDelete(Model model){
        return new ModelAndView("admin/delete");
    }

    @GetMapping("/api/products")
    public String getProducts() {
        String jsonProducts = productService.getJsonAllProducts();
        return jsonProducts;
    }

    @PostMapping("/api/products/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestParam("id") int id, @RequestParam("name") String name,
                           @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl,
                           @RequestParam("options") String options) {
        productService.saveProduct(id,name,price,imageUrl,options);

    }

    @PostMapping("/api/products/delete")
    public void deleteProduct(@RequestParam("id") int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/api/products/modify")
    public void modifyProduct(@RequestParam("id") int id, @RequestParam("name") String name,
                              @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl,
                              @RequestParam("options") String options) {
        productService.modifyProduct(id,name,price,imageUrl,options);
    }

    @GetMapping("/api/product/{id}")
    public String getProduct(@PathVariable int id){
        String product = productService.getProductByID(id);

        return product;
    }

}

