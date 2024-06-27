package gift.presentation;

import gift.application.CustomerService;
import gift.domain.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerManageController {

    private final CustomerService customerService;

    @Autowired
    public CustomerManageController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customerList = customerService.getCustomers();
        return ResponseEntity.ok(customerList);
    }

    @PostMapping("")
    public ResponseEntity<Object> addCustomer(@RequestBody CreateCustomerRequestDTO createCustomerRequestDTO) {
        customerService.addCustomer(
            createCustomerRequestDTO.name(),
            createCustomerRequestDTO.email(),
            createCustomerRequestDTO.phone(),
            createCustomerRequestDTO.address()
        );

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateCustomer(
        @PathVariable Long id,
        @RequestBody UpdateCustomerRequestDTO updateCustomerRequestDTO) {
        customerService.updateCustomer(
            id,
            updateCustomerRequestDTO.name(),
            updateCustomerRequestDTO.email(),
            updateCustomerRequestDTO.phone(),
            updateCustomerRequestDTO.address()
        );

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    public record CreateCustomerRequestDTO(String name, String email, String phone, String address) {

    }

    public record UpdateCustomerRequestDTO(Long id, String name, String email, String phone, String address) {

    }
}


