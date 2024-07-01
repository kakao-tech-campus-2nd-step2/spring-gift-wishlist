package gift.controller.admin;

import gift.domain.Product;
import gift.service.ProductService;
import gift.util.ImageStorageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam Long price,
                             @RequestParam String description,
                             @RequestPart MultipartFile imageFile) {
        try {
            // 이미지 저장
            String imagePath = ImageStorageUtil.saveImage(imageFile, null);

            // 이미지 URL을 Base64로 인코딩
            String imageUrl = ImageStorageUtil.encodeImagePathToBase64(imagePath);

            // 제품 정보 저장
            Product product = new Product(null, name, price, description, imageUrl);
            productService.addProduct(product);

            return "redirect:/admin/products";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam Long price,
                                @RequestParam String description,
                                @RequestPart MultipartFile imageFile) {
        try {
            Product product = productService.getProductById(id);

            if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
                ImageStorageUtil.deleteImage(ImageStorageUtil.decodeBase64ImagePath(product.getImageUrl()));
            }

            String imagePath = ImageStorageUtil.saveImage(imageFile, id);
            String imageUrl = ImageStorageUtil.encodeImagePathToBase64(imagePath);

            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImageUrl(imageUrl);

            productService.updateProduct(product);

            return "redirect:/admin/products";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null && product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            ImageStorageUtil.deleteImage(ImageStorageUtil.decodeBase64ImagePath(product.getImageUrl()));
        }
        productService.deleteProduct(id);

        return "redirect:/admin/products";
    }
}