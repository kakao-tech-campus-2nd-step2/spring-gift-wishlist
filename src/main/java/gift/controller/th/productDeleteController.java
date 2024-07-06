//package gift.controller.th;
//
//
//import gift.dto.ProductDTO;
//import gift.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class productDeleteController {
//
//    @Autowired
//    ProductService ps;
//
//    @DeleteMapping("/product")
//    public ModelAndView thDeleteProduct(@RequestBody List<Long> productIds){
//
//        for (Long productId : productIds) {
//            ps.deleteProduct(productId);
//        }
//
//        Map<Long, ProductDTO> map= ps.getAllProducts();
//
//        ModelAndView mav= new ModelAndView( "product/index");
//        mav.addObject("productMap",map);
//        return mav;
//    }
//}
