package gift.controller;

import gift.exception.CustomException;
import gift.dto.ProductDTO;
import gift.dto.ResponseDTO;
import gift.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Validated
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private static final String CRITICAL_ERROR_MESSAGE = "확인되지 않은 에러입니다. 관리자에게 문의 주세요";
    private static final String WELL_DONE_MESSAGE = "Success";

    @Autowired //생성자 주입
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getProducts(Model model) {
        model.addAttribute("productList", productService.getProductList());
        return "getProducts";
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> addProduct(@RequestBody @Valid ProductDTO productDTO) {
        try {
            productService.addProduct(productDTO);
        } catch (RuntimeException e) {
            return responseError(e);
        }
        return new ResponseEntity<>(new ResponseDTO(false, WELL_DONE_MESSAGE), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable @Min(1) @NotNull Integer id) {
        try {
            productService.deleteProduct(id);
        } catch (RuntimeException e) {
            return responseError(e);
        }
        return new ResponseEntity<>(new ResponseDTO(false, WELL_DONE_MESSAGE), HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable @Min(1) @NotNull Integer id,
            @RequestBody @Valid ProductDTO productDTO) {
        try {
            productService.updateProduct(id, productDTO);

        } catch (RuntimeException e) {
            return responseError(e);
        }
        return new ResponseEntity<>(new ResponseDTO(false, WELL_DONE_MESSAGE), HttpStatus.OK);
    }



    private ResponseEntity<ResponseDTO> responseError(RuntimeException e) {
        if (e instanceof CustomException) {
            return new ResponseEntity<>(new ResponseDTO(true, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        e.printStackTrace();
        return new ResponseEntity<>(new ResponseDTO(true, CRITICAL_ERROR_MESSAGE),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
