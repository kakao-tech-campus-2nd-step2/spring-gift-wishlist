package gift;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 상품 목록을 보여주는 products.html 을 렌더링하여 반환
     *
     * @return products.html
     */
    @GetMapping("")
    public String getProducts(Model model) {
        List<Product> products = productDao.selectProducts();
        model.addAttribute("products", products);
        return "products";
    }

    /**
     * 상품을 추가하는 페이지인 addForm.html 반환
     *
     * @return addForm.html
     */
    @GetMapping("/add")
    public String addProductForm(Model model) {
        return "addForm";
    }

    /**
     * 추가할 상품의 정보를 받아서 저장 <br> 이미 존재하는 상품 id이면 저장하지 않음
     *
     * @return 상품 목록 페이지로 리다이렉션
     */
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(
        @RequestParam("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("price") Long price,
        @RequestParam("imageUrl") String imageUrl) {
        try {
            productDao.insertNewProduct(new Product(id, name, price, imageUrl));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("이미 존재하는 id입니다.");
        }
        return ResponseEntity.ok("/api/products");
    }

    /**
     * 상품 정보를 수정하는 페이지인 editForm.html 반환 <br> 상품의 기존 정보를 model에 담아서 반환
     *
     * @param id 수정할 상품의 id
     * @return editForm.html
     */
    @GetMapping("/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productDao.selectOneProduct(id));
        return "editForm";
    }

    /**
     * 수정된 상품 정보를 받아서 데이터를 갱신
     *
     * @return 상품 목록 페이지로 리다이렉션
     */
    @PutMapping("/edit")
    public ResponseEntity<String> editProduct(
        @RequestParam("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("price") Long price,
        @RequestParam("imageUrl") String imageUrl) {
        try {
            productDao.updateProduct(new Product(id, name, price, imageUrl));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }
        return ResponseEntity.ok("/api/products");
    }

    /**
     * 상품을 삭제
     *
     * @param id 삭제할 상품의 id
     * @return 상품 목록 페이지로 리다이렉션
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id) {
        try {
            productDao.deleteProduct(id);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }
        return ResponseEntity.ok("/api/products");
    }
}
