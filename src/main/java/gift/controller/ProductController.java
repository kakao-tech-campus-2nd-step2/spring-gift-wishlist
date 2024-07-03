package gift.controller;

import gift.domain.Product;
import gift.domain.ProductRequestDTO;
import gift.service.ProductService;
import gift.util.ImageStorageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestParam String name,
                                              @RequestParam Long price,
                                              @RequestParam String description,
                                              @RequestPart MultipartFile imageFile) throws Exception {
        // DTO 객체에 데이터 할당
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(name, price, description);

        // 유효성 검사
        productRequestDTO.validate();

        // 이미지 저장 및 URL 인코딩 (이 부분은 ImageStorageUtil 메소드 사용으로 가정)
        String imagePath = ImageStorageUtil.saveImage(imageFile);
        String imageUrl = ImageStorageUtil.encodeImagePathToBase64(imagePath);

        // Product 객체 생성 및 저장
        Product product = new Product(null, productRequestDTO.getName(), productRequestDTO.getPrice(),
                productRequestDTO.getDescription(), imageUrl);
        productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestPart MultipartFile imageFile,
                                                 @RequestParam String name,
                                                 @RequestParam Long price,
                                                 @RequestParam String description) throws Exception {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

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

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            ImageStorageUtil.deleteImage(ImageStorageUtil.decodeBase64ImagePath(product.getImageUrl()));
        }

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/images/{base64EncodedPath}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageByEncodedPath(@PathVariable String base64EncodedPath) throws IOException {
        // 이미지 경로 디코딩
        String imagePath = ImageStorageUtil.decodeBase64ImagePath(base64EncodedPath);

        // 이미지 바이트 전환
        byte[] imageBytes = java.nio.file.Files.readAllBytes(new File(imagePath).toPath());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
}
