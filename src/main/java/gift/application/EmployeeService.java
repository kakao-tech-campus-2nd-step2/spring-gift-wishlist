package gift.application;

import gift.domain.Employee;
import gift.infra.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public void addEmployee(String name, String email, String address, String phone) {
        Employee customer = new Employee(name, email, address, phone);
        employeeRepository.addCustomer(customer);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteCustomer(id);
    }

    public void updateCustomer(Long id, String name, String email, String address, String phone) {
        Employee customer = new Employee(name, email, address, phone
        );
        employeeRepository.updateCustomer(id, customer);
    }

    public Employee getCustomerByName(Long id) {
        return employeeRepository.getCustomerById(id);
    }

    public List<Employee> getCustomers() {
        return employeeRepository.getCustomers();
    }

}
