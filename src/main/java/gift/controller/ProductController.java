package gift.controller;

import gift.dto.ProductDTO;
import gift.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 상품 추가,수정,삭제,조회를 위한 api end-point
 * <p>
 * $/api/products
 */
@RestController
public class ProductController {

    private final ProductService pm;

    public ProductController(ProductService pm) {
        this.pm = pm;
    }

    /**
     * 상품 전체 목록 반환
     * @return 상품 DTO
     */
    @GetMapping("/api/products")
    public List<ProductDTO> getList(){
        List<ProductDTO> dto = pm.readAll();
        return dto;
    }

    /**
     * 새로운 상품 생성
     * @param dto id가 존재하는 상태로 입력되더라도 무시됨.
     */
    @PostMapping("/api/products")
    public void add(ProductDTO dto){
        pm.create(dto);
    }

    /**
     * 기존 상품 수정
     * @param id 수정하고자 하는 상품의 id
     * @param dto 수정하고자 하는 값 이외 null로 지정
     */
    @PutMapping("/api/products")
    public void update(@RequestParam("id") Long id, @RequestBody ProductDTO dto){
        if(id==null){
            throw new IllegalArgumentException("id를 입력해주세요");
        }
        changeCheckAndUpdate(id, dto);
    }

    @DeleteMapping("/api/products")
    public void delete(@RequestParam("id") Long id){
        pm.delete(id);
    }

    private void changeCheckAndUpdate(Long id, ProductDTO dto) {
        if (dto.getName()!=null){
            pm.updateName(id, dto.getName());
        }
        if (dto.getPrice()!=null){
            pm.updatePrice(id, dto.getPrice());
        }
        if (dto.getImageUrl()!=null){
            pm.updateImageUrl(id, dto.getImageUrl());
        }
    }


}