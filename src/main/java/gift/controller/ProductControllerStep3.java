package gift.controller;

import gift.exceptions.KakaoContainException;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import gift.entity.Product;
import gift.dto.*;
import java.util.List;


@Controller
@Validated
@RequestMapping("/")
public class ProductControllerStep3 {
    private JdbcTemplate jdbcTemplate;

    public ProductControllerStep3(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("v3/products")
    public String getAllProducts(Model model) {
        String sql = "select id, name, price, imageurl from products";
        List<Product> products = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Product product = new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getString("imageurl")
                    );
                    return product;
                });
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("v3/products")
    public String addProduct(@Valid  @RequestBody ProductDTO productDTO) {
        if (productDTO.name().contains("카카오")) {
            throw new KakaoContainException("이름에 카카오는 포함할 수 없습니다. 수정해 주세요");
        }

        String sql = "INSERT INTO products(id, name, price, imageurl) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());

        return "redirect:/v3/products";
    }

    @PostMapping("/v3/products/{id}")
    public String modifyProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        if (productDTO.name().contains("카카오")) {
            throw new KakaoContainException("이름에 카카오는 포함할 수 없습니다. 수정해 주세요");
        }

        String updateSql = "UPDATE products SET name = ?, price = ?, imageurl = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, productDTO.name(), productDTO.price(), productDTO.imageUrl(), id);

        return "redirect:/v3/products";
    }

    @DeleteMapping("v3/products/{id}")
    public String DeleteProduct(@PathVariable("id") Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);

        return "redirect:/v3/products";
    }
}