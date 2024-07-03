package gift.controller.admin;

import gift.domain.Product;
import gift.domain.ProductRequestDTO;
import gift.service.ProductService;
import gift.util.ImageStorageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }
    private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

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
                             @RequestPart MultipartFile imageFile) throws IOException {
        // DTO 객체에 데이터 할당
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(name, price, description);

        // 유효성 검사
        productRequestDTO.validate();

        // 이미지 저장 및 URL 인코딩 (이 부분은 ImageStorageUtil 메소드 사용으로 가정)
        String imagePath = ImageStorageUtil.saveImage(imageFile);
        String imageUrl = ImageStorageUtil.encodeImagePathToBase64(imagePath);

        // Product 객체 생성 및 저장
        Product product = new Product(null, productRequestDTO.getName(), productRequestDTO.getPrice(), productRequestDTO.getDescription(), imageUrl);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam Long price,
                                @RequestParam String description,
                                @RequestPart MultipartFile imageFile) throws IOException {
        Product product = productService.getProductById(id);

        // 이미지 업데이트 시 이전 이미지 삭제
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            ImageStorageUtil.deleteImage(ImageStorageUtil.decodeBase64ImagePath(product.getImageUrl()));
        }

        // 이미지 저장 및 URL 인코딩
        String imagePath = ImageStorageUtil.saveImage(imageFile);
        String imageUrl = ImageStorageUtil.encodeImagePathToBase64(imagePath);

        // DTO 객체 생성 및 데이터 할당
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(name, price, description);

        // 유효성 검사
        productRequestDTO.validate();

        // 상품 정보 업데이트
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setImageUrl(imageUrl);

        productService.updateProduct(product);

        return "redirect:/admin/products";
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


