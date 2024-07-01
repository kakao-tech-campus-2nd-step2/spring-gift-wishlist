package gift.controller;

import org.springframework.web.bind.annotation.*;

import gift.entity.Product;
import gift.dto.*;
import java.util.Map;
import java.util.HashMap;

@RestController
public class ProductControllerStep1 {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/api/products")
    public ProductsDTO getAllProducts() {
        return new ProductsDTO(products.values().stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getImageUrl()))
                .toArray(ProductDTO[]::new));
    }

    @GetMapping("/api/product/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long id) {
        Product product = products.get(id);
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl());
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {

        Product newProduct = new Product(productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());
        products.put(newProduct.getId(), newProduct);

        return new ProductDTO(newProduct.getId(),
                newProduct.getName(),
                newProduct.getPrice(),
                newProduct.getImageUrl());
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = products.get(id);
        if (productDTO.id() != null) {
            products.remove(id);
            updatedProduct.setId(productDTO.id());
            products.put(productDTO.id(), updatedProduct);
        }
        if (productDTO.name() != null) updatedProduct.setName(productDTO.name());
        if (productDTO.price() != null) updatedProduct.setPrice(productDTO.price());
        if (productDTO.imageUrl() != null) updatedProduct.setImageUrl(productDTO.imageUrl());

        return new ProductDTO(updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getImageUrl());
    }

    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable("id") Long id) {
        products.remove(id);

        return "DELETE SUCCESS";
    }
}