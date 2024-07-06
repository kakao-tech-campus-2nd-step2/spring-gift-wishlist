//package gift.controller.th;
//
//import gift.dto.ProductDTO;
//import gift.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Map;
//
//@Controller
//public class productListController {
//    @Autowired
//    ProductService ps;
//    @GetMapping("/products")
//    public ModelAndView thMap(){
//        //pc.createProduct(new CreateProduct.Request(1L,"이름",1000,"이미지 URL"));
//        Map<Long, ProductDTO> map= ps.getAllProducts();
//
//        ModelAndView mav= new ModelAndView( "product/index");
//        mav.addObject("productMap",map);
//        return mav;
//    }
//}
