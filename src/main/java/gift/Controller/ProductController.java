package gift.Controller;

import gift.model.Product;
import gift.model.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class ProductController {
    //private final Map<Long, Product> products = new HashMap<>();

    long id = 0L;

    private final ProductDao ProductDao;

    public ProductController(gift.model.ProductDao productDao) {
        ProductDao = productDao;
    }

    //모든 상품 반환
    @GetMapping("/getAllProducts")
    public String getProductsController(Model model){
        //List<Product> productList = products.values().stream().collect(Collectors.toList());
        List<Product> productList = ProductDao.selectAllProduct();
        model.addAttribute("productList", productList);
        return "index";
    }

    @GetMapping("/getAllProductList")
    @ResponseBody
    public List<Product> getProductsListController(Model model){
        //List<Product> productList = products.values().stream().collect(Collectors.toList());
        List<Product> productList = ProductDao.selectAllProduct();
        return productList;
    }

    //id 상품 하나 반환
    @GetMapping("/getProduct/{id}")
    @ResponseBody
    public Product getProductByIdController(@PathVariable Long id){
        Product product = ProductDao.selectProduct(id);
        return product;
        //return products.get(id);
    }

    //상품 추가
    @PostMapping("/postProduct")
    public String postProductController(@ModelAttribute Product product){
        id++;
        product.setId(id);
        ProductDao.insertProduct(product);
        //products.put(id, product);
        return "redirect:/api/getAllProducts";
    }

    //상품 삭제
    @GetMapping("/deleteProduct/{id}")
    public String deleteProductController(@PathVariable Long id){
        ProductDao.deleteProduct(id);
        //products.remove(id);
        return "redirect:/api/getAllProducts";
    }

    //상품 업데이트
    @PostMapping("/updateProduct/{id}")
    public String updateProductController(@PathVariable Long id, @ModelAttribute Product newProduct){
        Product oldProduct = ProductDao.selectProduct(id);
        //Product oldProduct = products.get(id);
        if(newProduct.getName() != null && !newProduct.getName().isEmpty()){
            oldProduct.setName(newProduct.getName());
        }
        if(newProduct.getPrice() != null){
            oldProduct.setPrice(newProduct.getPrice());
        }
        if(newProduct.getImageUrl() != null && !newProduct.getImageUrl().isEmpty()){
            oldProduct.setImageUrl(newProduct.getImageUrl());
        }
        //products.replace(id, oldProduct);
        ProductDao.updateProduct(oldProduct);
        return "redirect:/api/getAllProducts";
    }
}
