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
        Employee employee = new Employee(name, email, address, phone);
        employeeRepository.addEmployee(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployee(id);
    }

    public void updateEmployee(Long id, String name, String email, String address, String phone) {
        Employee employee = new Employee(name, email, address, phone
        );
        employeeRepository.updateEmployee(id, employee);
    }

    public Employee getEmployeeByName(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

}
