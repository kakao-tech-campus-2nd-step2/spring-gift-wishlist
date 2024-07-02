package gift.Controller;

import gift.Exception.ErrorCode;
import gift.Exception.ProductNameException;
import gift.Model.Product;
import gift.Service.InputValidity;
import gift.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/api/products/add")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "post";
    }

    @PostMapping("/api/products")
    public String createProduct(@ModelAttribute Product product) {
        //테스트
        if(InputValidity.checkLengthValidity(product.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_LENGTH);
        }
        if(InputValidity.checkSpecialSymbolValidity(product.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_SPECIAL_SYMBOL);
        }
        if(InputValidity.checkKakaoValidity(product.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_KAKAO);
        }
        if(InputValidity.checkPriceValidity(product.getPrice())){
            throw new ProductNameException(ErrorCode.INVALID_PRICE);
        }
        productService.addProduct(product);
        return "redirect:/api/products";
    }

    @GetMapping("/api/products/update/{id}")
    public String editProductForm(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update";
    }

    @PostMapping("/api/products/update/{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, @ModelAttribute Product newProduct) {
        if(InputValidity.checkLengthValidity(newProduct.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_LENGTH);
        }
        if(InputValidity.checkSpecialSymbolValidity(newProduct.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_SPECIAL_SYMBOL);
        }
        if(InputValidity.checkKakaoValidity(newProduct.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_KAKAO);
        }
        if(InputValidity.checkPriceValidity(newProduct.getPrice())){
            throw new ProductNameException(ErrorCode.INVALID_PRICE);
        }
        productService.updateProduct(newProduct);
        return "redirect:/api/products";
    }

    @PostMapping("/api/products/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/api/products";
    }
}
