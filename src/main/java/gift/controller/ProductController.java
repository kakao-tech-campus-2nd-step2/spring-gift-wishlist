package gift.controller;

import gift.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    ProductController(){
        products.put(1L, new Product(1L, "AAA", 123, "adsfiewfjo"));
        products.put(2L, new Product(2L, "BBB", 456, "eroguhensjkn"));
    }

    // 상품 모두 조회
    @GetMapping("/api/products")
    public String responseAllProducts(Model model){
        List<Product> productsList = new ArrayList<>(products.values());
        model.addAttribute("products", productsList);
        return "index";
    }


    // 상품 추가 폼
    @GetMapping("/api/products/new-form")
    public String newProductForm(Model model) {
        return "new-product-form";
    }

    // 상품 추가
    @PostMapping("/api/products")
    public String addOneProduct(@ModelAttribute Product product) {
        System.out.println(product);
        long id = idGenerator.incrementAndGet();
        products.put(id, new Product(id, product.getName(), product.getPrice(), product.getImageUrl()));
        return "redirect:/api/products";
    }

    // 상품 수정 폼
    @GetMapping("/api/products/edit/{id}")
    public String editProductForm(@PathVariable("id") long id, Model model) {
        Product product = products.get(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "modify-product-form";
        }
        return "redirect:/api/products";
    }

    // 상품 수정
    @PostMapping("/api/products/modify/{id}")
    public String modifyOneProduct(@PathVariable("id") long id, @ModelAttribute Product product) {
        if (products.containsKey(id)) {
            product.setId(id); // Ensure the ID remains the same
            products.put(id, product);
        }
        return "redirect:/api/products";
    }

    // 상품 삭제
    @GetMapping("/api/products/delete/{id}")
    public String deleteOneProduct(@PathVariable("id") long id) {
        products.remove(id);
        return "redirect:/api/products";
    }

    // 선택된 상품 삭제
    @PostMapping("/api/products/delete-selected")
    public ResponseEntity<String> deleteSelectedProducts(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            products.remove(id);
        }
        return new ResponseEntity<>("Selected products deleted successfully.", HttpStatus.OK);
    }
}
