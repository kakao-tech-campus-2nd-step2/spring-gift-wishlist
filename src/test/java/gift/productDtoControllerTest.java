package gift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import gift.Controller.ProductController;
import gift.DTO.ProductDto;
import gift.Repository.ProductDao;
import gift.Service.ProductService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test") // 테스트 프로파일 활성화
public class productDtoControllerTest {

  @Autowired
  private ProductService productService;
  @Autowired
  private ProductController productController;
  @Autowired
  private ProductDao productDao;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @BeforeEach
  public void setUp() {
    productController = new ProductController(productService);
    jdbcTemplate.execute("DROP TABLE IF EXISTS product");
    jdbcTemplate.execute("CREATE TABLE product (" +
      "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
      "name VARCHAR(255)," +
      "price BIGINT," +
      "imageUrl VARCHAR(255)" +
      ")");
  }

  @AfterEach
  public void tearDown() {
    // 데이터베이스 정리
    jdbcTemplate.execute("DELETE FROM product");
  }

  @Test
  @DisplayName("Test : getAllProducts")
  public void testGetAllProducts() {
    // 제품 추가
    ProductDto productDto1 = new ProductDto(1L, "Product 1", 100,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
    ProductDto productDto2 = new ProductDto(2L, "Product 2", 200,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc94364879792549ads8bdd8a3.jpg");
    productDao.insertProduct(productDto1);
    productDao.insertProduct(productDto2);
    // getAllProducts() 호출
    List<ProductDto> returnedProductDtos = productDao.selectAllProducts();
    // 반환된 제품 리스트 검증
    assertEquals(2, returnedProductDtos.size());
    assertEquals(productDto1.getId(), returnedProductDtos.get(0).getId());
    assertEquals(productDto1.getName(), returnedProductDtos.get(0).getName());
    assertEquals(productDto1.getPrice(), returnedProductDtos.get(0).getPrice());
    assertEquals(productDto1.getImageUrl(), returnedProductDtos.get(0).getImageUrl());
    assertEquals(productDto2.getId(), returnedProductDtos.get(1).getId());
    assertEquals(productDto2.getName(), returnedProductDtos.get(1).getName());
    assertEquals(productDto2.getPrice(), returnedProductDtos.get(1).getPrice());
    assertEquals(productDto2.getImageUrl(), returnedProductDtos.get(1).getImageUrl());

  }


  @Test
  @DisplayName("Test : getProductById")
  public void testGetProductById() {
    // 제품 추가
    ProductDto productDTO = new ProductDto(1L, "Product 1", 100,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
    productDao.insertProduct(productDTO);

    // getProductById() 호출 - 존재하는 제품 ID
    ProductDto selectProduct = productDao.selectProduct(1L);

    // 반환된 제품 검증
    assertEquals(productDTO.getId(), selectProduct.getId());
    assertEquals(productDTO.getName(), selectProduct.getName());
    assertEquals(productDTO.getPrice(), selectProduct.getPrice());
    assertEquals(productDTO.getImageUrl(), selectProduct.getImageUrl());
  }

  @Test
  @DisplayName("Test : addProduct")
  public void testAddProduct() {
    ProductDto newProductDto = new ProductDto(1L, "아이스 카페 아메리카노", 4500,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

    productDao.insertProduct(newProductDto);

    assertNotNull(newProductDto);
    assertNotNull(newProductDto.getId());
    assertEquals("아이스 카페 아메리카노", newProductDto.getName());
    assertEquals(4500, newProductDto.getPrice());
    assertEquals(
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg",
      newProductDto.getImageUrl());
  }


  @Test
  @DisplayName("Test : updateProduct")
  void testUpdateProduct() {
    // 기존 제품 추가
    ProductDto existingProductDto = new ProductDto(1L, "아이스 카페 아메리카노", 4500,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
    productDao.insertProduct(existingProductDto);

    // 업데이트할 제품 정보
    ProductDto updatedProductDto = new ProductDto(1L, "핫 카페 아메리카노", 4000,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
    // 제품 업데이트 요청
    productDao.updateProduct(1L, updatedProductDto);
    // 업데이트된 제품 받아오기

    assertNotNull(updatedProductDto);
    assertEquals(1L, updatedProductDto.getId());
    assertEquals("핫 카페 아메리카노", updatedProductDto.getName());
    assertEquals(4000, updatedProductDto.getPrice());
    assertEquals(
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg",
      updatedProductDto.getImageUrl());
  }

  @Test
  @DisplayName("Test : deleteProduct")
  public void testDeleteProduct() {
    // 제품 추가
    ProductDto productDTO = new ProductDto(1L, "Product 1", 100,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
    productDao.insertProduct(productDTO);

    // deleteProduct() 호출 - 존재하는 제품 ID
    productDao.deleteProduct(1L);

    // ProductService를 통해 제품이 삭제되었는지 확인
    assertThrows(EmptyResultDataAccessException.class, () -> {
      productDao.selectProduct(1L); // 삭제된 제품 조회 시 예외가 발생해야 함
    });
  }

  @Test
  @DisplayName("Test : nameSizeValidate")
  public void testSizeValidate() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    ProductDto invalidProduct1DTO = new ProductDto(1L, "pppppppppsdfsfdsppppppppProduct 1", 100,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

    Set<ConstraintViolation<ProductDto>> violations1 = validator.validate(invalidProduct1DTO);

    assertThrows(ConstraintViolationException.class, () -> {
      throw new ConstraintViolationException(violations1);
    });
  }

  @Test
  @DisplayName("Test : nameBlankValidate")
  public void testBlankValidate() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    ProductDto invalidProduct2DTO = new ProductDto(2L, null, 100,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

    Set<ConstraintViolation<ProductDto>> violations = validator.validate(invalidProduct2DTO);

    assertThrows(ConstraintViolationException.class, () -> {
      throw new ConstraintViolationException(violations);
    });
  }

  @Test
  @DisplayName("Test : nameExpressionValidate")
  public void testExpressionValidate() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    ProductDto invalidProduct2DTO = new ProductDto(1L, "!product", 100,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

    Set<ConstraintViolation<ProductDto>> violations = validator.validate(invalidProduct2DTO);

    assertThrows(ConstraintViolationException.class, () -> {
      throw new ConstraintViolationException(violations);
    });
  }

  @Test
  @DisplayName("Test : nameSpecialNameValidate - 카카오")
  public void testSpecialNameValidate() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    ProductDto invalidProduct2DTO = new ProductDto(1L, "카카오product", 100,
      "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

    Set<ConstraintViolation<ProductDto>> violations = validator.validate(invalidProduct2DTO);

    assertThrows(ConstraintViolationException.class, () -> {
      throw new ConstraintViolationException(violations);
    });
  }
}