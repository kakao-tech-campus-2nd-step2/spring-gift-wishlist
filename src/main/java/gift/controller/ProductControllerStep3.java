package gift.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import gift.entity.Product;
import gift.utility.nameValidator;
import gift.dto.*;
import java.util.List;


@Controller
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
    public String addProduct(@ModelAttribute ProductDTO productDTO) {
        String sql = "INSERT INTO products(id, name, price, imageurl) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());

        nameValidator.nameValidate(productDTO.name());
        return "redirect:/v3/products";
    }

    @PostMapping("/v3/products/{id}")
    public String modifyProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
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