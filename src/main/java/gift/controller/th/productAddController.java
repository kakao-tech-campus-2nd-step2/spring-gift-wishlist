package gift.controller.th;

import gift.dto.CreateProduct;
import gift.dto.ProductDTO;
import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class productAddController {
    @Autowired
    ProductService ps;

//    @PostMapping("/product")
//    public ModelAndView thAddProduct(@ModelAttribute CreateProduct.Request request){
//
//        boolean isSaved = ps.createProduct(request);
//
//        Map<Long, ProductDTO> map= ps.getAllProducts();
//
//        ModelAndView mav= new ModelAndView( "product/index");
//        mav.addObject("productMap",map);
//        return mav;
//    }
}

