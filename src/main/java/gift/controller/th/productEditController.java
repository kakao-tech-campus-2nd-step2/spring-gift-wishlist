//package gift.controller.th;
//
//import gift.dto.EditProduct;
//import gift.dto.ProductDTO;
//import gift.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Map;
//
//@Controller
//public class productEditController {
//    @Autowired
//    ProductService ps;
//
//    @PutMapping("/product")
//    public ModelAndView thEditProduct(@ModelAttribute EditProduct.Request request){
//
//        ps.editProductDetail(request.getId(),request);
//
//        Map<Long, ProductDTO> map= ps.getAllProducts();
//
//        ModelAndView mav= new ModelAndView( "product/index");
//        mav.addObject("productMap",map);
//        return mav;
//    }
//}
