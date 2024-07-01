package gift.service;

import gift.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    /*
    상품 수정 시 만약 문제가 생기면 어떤 값에 의해서 생기는 지 파악의 어려움을 막기 위해
    update를 각 항목마다 분리
     */

    //상품 리스트 전체 조회
    List<ProductDTO> readAll();
    //새 상품 생성
    void create(ProductDTO prod);
    //상품 이름 변경
    void updateName(long id,String name);
    //상품 가격 변경
    void updatePrice(long id,int price);
    //상품 이미지 변경
    void updateImageUrl(long id,String url);
    //상품 삭제
    void delete(long id);

}
