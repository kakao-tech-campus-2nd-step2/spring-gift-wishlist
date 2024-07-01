package gift.controller;

import gift.DTO.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FormController {
    private final JdbcTemplate jdbcTemplate;
    public FormController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    /*
     * 관리자 페이지 기본 화면 연결을 위한 Controller
     */
    @GetMapping("/")
    public String start(){
        return "index.html";
    }
    /*
     * Product를 수정하기 위해 정보 입력 HTML Form 열기
     * 이전 화면 : 상품 목록 -> 현재 -> 다음 화면 : 상품 수정 폼
     */
    @GetMapping("/api/products/modForm/{productId}")
    public String modForm(@PathVariable("productId") Long product_id, Model model){
        List<Product> product = jdbcTemplate.query(
                "SELECT id, name, price, imageUrl FROM products WHERE id = ?", new Object[]{product_id},
                (rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("imageUrl")
                )
        );
        model.addAttribute("product", product.get(0));
        return "productModForm.html";
    }
}
