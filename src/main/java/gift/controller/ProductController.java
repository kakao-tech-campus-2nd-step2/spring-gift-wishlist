package gift.controller;

import gift.dto.ProductRequest;
import gift.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public ProductController(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = jdbcTemplate.query(
                "SELECT * FROM products",
                (rs, rowNum) -> new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price"), rs.getString("imageUrl"))
        );
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String getProduct(@PathVariable long id, Model model) {
        Product product = jdbcTemplate.queryForObject(
                "SELECT * FROM products WHERE id = ?",
                new Object[]{id},
                (rs, rowNum) -> new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price"), rs.getString("imageUrl"))
        );
        if (product == null) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        model.addAttribute("product", product);
        return "editForm";
    }

    @GetMapping("/new")
    public String addProductForm(Model model) {
        model.addAttribute("productRequest", new ProductRequest("", 0, ""));
        return "addForm";
    }

    @PostMapping
    public String addProduct(@ModelAttribute ProductRequest productRequest) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", productRequest.name());
        parameters.put("price", productRequest.price());
        parameters.put("imageUrl", productRequest.imageUrl());
        jdbcInsert.executeAndReturnKey(parameters);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductRequest productRequest) {
        int rows = jdbcTemplate.update(
                "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?",
                productRequest.name(), productRequest.price(), productRequest.imageUrl(), id
        );
        if (rows == 0) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        int rows = jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
        if (rows == 0) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        return "redirect:/products";
    }

    @PostMapping("/delete-batch")
    @ResponseBody
    public String deleteBatch(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("ids");
        for (Long id : ids) {
            jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
        }
        return "Success";
    }
}
