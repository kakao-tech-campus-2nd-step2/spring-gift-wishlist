package gift.Controller;

import gift.Model.Product;
import gift.Model.ProductDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO){
        this.productDAO = productDAO;
        try {
            productDAO.createProductTable();
        } catch (Exception e) {
            System.err.println("에러 발생: " + e.getMessage());
        }
    }

    @PostMapping("/products")
    @ResponseBody
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        try {
            productDAO.insertProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("추가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 실패");
        }
    }

    @DeleteMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        try {
            productDAO.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body("제거 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("제거 실패");
        }
    }

    @PutMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity< String> updateProduct(@PathVariable Long id, @RequestBody Product product){
        try {
            productDAO.updateProduct(id, product);
            return ResponseEntity.status(HttpStatus.OK).body("업데이트 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }

    @GetMapping("/products")
    public List<Product> viewAllProducts(){
        return productDAO.selectAllProduct();
    }

    @GetMapping("/products/{id}")
    public Product viewProduct(@PathVariable Long id, Model model){
        return productDAO.selectProduct(id);
    }
}
