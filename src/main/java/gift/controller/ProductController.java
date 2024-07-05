package gift.controller;

import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class  ProductController {
    @Autowired
    private ProductService productService;


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/products/delete")
    public void deleteProduct(@RequestParam("id") int id) {
        productService.deleteProduct(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/products/modify")
    public void modifyProduct(@RequestParam("id") int id, @RequestParam("name") String name,
                              @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl,
                              @RequestParam("options") String options) {
        productService.modifyProduct(id,name,price,imageUrl,options);
    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/api/product/{id}")
    public String getProduct(@PathVariable int id){
        String product = productService.getProductByID(id);
        return product;
    }
}

