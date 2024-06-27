package gift.application;

import gift.domain.Customer;
import gift.infra.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(String name, String email, String address, String phone) {
        Customer customer = new Customer(name, email, address, phone);
        customerRepository.addCustomer(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteCustomer(id);
    }

    public void updateCustomer(Long id, String name, String email, String address, String phone) {
        Customer customer = new Customer(name, email, address, phone
        );
        customerRepository.updateCustomer(id, customer);
    }

    public Customer getCustomerByName(Long id) {
        return customerRepository.getCustomerById(id);
    }

    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

}
