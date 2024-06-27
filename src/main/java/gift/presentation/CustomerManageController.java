package gift.presentation;

import gift.application.CustomerService;
import gift.domain.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerManageController {

    private final CustomerService customerService;

    @Autowired
    public CustomerManageController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public String getCustomers(Model model) {
        List<Customer> customerList = customerService.getCustomers();
        model.addAttribute("customers", customerList);
        model.addAttribute("newCustomer", new CreateCustomerRequestDTO("", "", "", ""));
        model.addAttribute("pageSize", customerList.size());
        model.addAttribute("totalEntries", customerList.size());
        return "employees.html";
    }

    @PostMapping("")
    public String addCustomer(@ModelAttribute CreateCustomerRequestDTO createCustomerRequestDTO) {
        customerService.addCustomer(
            createCustomerRequestDTO.name(),
            createCustomerRequestDTO.email(),
            createCustomerRequestDTO.phone(),
            createCustomerRequestDTO.address()
        );
        return "redirect:/customers/";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    public record CreateCustomerRequestDTO(String name, String email, String phone, String address) {

    }
}
