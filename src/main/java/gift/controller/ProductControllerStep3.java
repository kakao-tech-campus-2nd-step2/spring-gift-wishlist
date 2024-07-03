package gift.controller;

import gift.exceptions.KakaoContainException;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import gift.entity.Product;
import gift.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Validated
@RequestMapping("/")
public class ProductControllerStep3 {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductControllerStep3(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    @GetMapping("v3/products")
    public String getAllProducts(Model model) {
        List<Product> products = getProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("v3/products")
    public String addProduct(@Valid  @RequestBody ProductDTO productDTO) {
        validateProductName(productDTO.name());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", productDTO.name());
        parameters.put("price", productDTO.price());
        parameters.put("imageurl", productDTO.imageUrl());

        simpleJdbcInsert.execute(parameters);

        return "redirect:/v3/products";
    }

    @PostMapping("/v3/products/{id}")
    public String modifyProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        validateProductName(productDTO.name());

        updateProduct(id, productDTO);

        return "redirect:/v3/products";
    }

    @DeleteMapping("v3/products/{id}")
    public String DeleteProduct(@PathVariable("id") Long id) {
        deleteProductById(id);

        return "redirect:/v3/products";
    }

    private List<Product> getProducts() {
        String sql = "SELECT id, name, price, imageurl FROM products";
        return jdbcTemplate.query(
                sql, (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("imageurl")
                ));
    }

    private void updateProduct(Long id, ProductDTO productDTO) {
        String sql = "UPDATE products SET name = ?, price = ?, imageurl = ? WHERE id = ?";
        jdbcTemplate.update(sql, productDTO.name(), productDTO.price(), productDTO.imageUrl(), id);
    }

    private void deleteProductById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private void validateProductName(String name) {
        if (name.contains("카카오")) {
            throw new KakaoContainException("이름에 카카오는 포함할 수 없습니다. 수정해 주세요");
        }
    }
}