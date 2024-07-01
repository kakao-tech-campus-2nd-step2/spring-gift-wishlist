package gift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;


@RestController
public class  ProductController {
    //private Map<Long, Product> products = new HashMap<>();
    //private static Long count = 1L;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/admin/products")
    public ModelAndView adminProducts(Model model){
        var sql = "select * from PRODUCT inner join option on product.id = option.id";
        List<Product> products = jdbcTemplate.query(sql,(rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl"),
                rs.getString("option")
        ));
        model.addAttribute("products", products);
        return new ModelAndView("admin/products");
    }
    @GetMapping("/admin/add")
    public ModelAndView adminProductsAdd(Model model){
        return new ModelAndView("admin/add");
    }
    @GetMapping("/admin/modify")
    public ModelAndView adminProductsModify(Model model){
        return new ModelAndView("admin/modify");
    }
    @GetMapping("/admin/delete")
    public ModelAndView adminProductsDelete(Model model){
        return new ModelAndView("admin/delete");
    }

    @GetMapping("/api/products")
    public String getProducts() {

        String allProducts = "";
        ObjectMapper objectMapper = new ObjectMapper();
        var sql = "select * from product inner join option on product.id = option.id";
        List<Product> products = jdbcTemplate.query(sql,(rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl"),
                rs.getString("option")
        ));
        try {
            allProducts = objectMapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    @PostMapping("/api/products/add")
    public void addProduct(@RequestParam("id") int id, @RequestParam("name") String name,
                           @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl,
                           @RequestParam("options") String options) {

        var sql = "insert into product(id,name,price,imageUrl) values (?,?,?,?)";
        jdbcTemplate.update(sql, id,name,price,imageUrl);
        //jdbcTemplate.execute(sql);

        List<String> optionList = Arrays.stream(options.split(",")).toList();
        for(String opt : optionList){
            sql = "insert into option(id,option) values(?,?)";
            jdbcTemplate.update(sql, id, opt);
            //jdbcTemplate.execute(sql);
        }
        //products.put(count, product);
        //count++;
    }

    @PostMapping("/api/products/delete")
    public void deleteProduct(@RequestParam("id") int id) {
//        Iterator<Map.Entry<Long, Product>> iterator = products.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Long, Product> entry = iterator.next();
//            if (entry.getValue().id == id) {
//                iterator.remove();
//            }
//        }
        var sql = "delete from product where id = ?";
        jdbcTemplate.update(sql, id);
        //jdbcTemplate.execute(sql);

        sql ="delete from option where id = ?";
        jdbcTemplate.update(sql, id);
        //jdbcTemplate.execute(sql);

    }

    @PostMapping("/api/products/modify")
    public void modifyProduct(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl, @RequestParam("options") String options) {
//        Iterator<Map.Entry<Long, Product>> iterator = products.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Long, Product> entry = iterator.next();
//            if (entry.getValue().id == id) {
//                entry.getValue().name = name;
//                entry.getValue().price = price;
//                entry.getValue().imageUrl = imageUrl;
//                entry.getValue().options = Arrays.stream(options.split(",")).toList();
//            }
//        }
        deleteProduct(id);
        addProduct(id, name, price, imageUrl, options);
    }

//    public void setProducts(Map<Long, Product> products) {
//        this.products = products;
//    }

}

