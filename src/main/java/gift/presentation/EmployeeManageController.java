package gift.presentation;

import gift.application.EmployeeService;
import gift.domain.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeManageController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeManageController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public String getEmployees(Model model) {
        List<Employee> employeeList = employeeService.getEmployees();
        model.addAttribute("employees", employeeList);
        model.addAttribute("newEmployee", new CreateEmployeeRequestDTO("", "", "", ""));
        model.addAttribute("pageSize", employeeList.size());
        model.addAttribute("totalEntries", employeeList.size());
        return "employees.html";
    }

    @PostMapping("")
    public String addEmployee(@ModelAttribute CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        employeeService.addEmployee(
            createEmployeeRequestDTO.name(),
            createEmployeeRequestDTO.email(),
            createEmployeeRequestDTO.phone(),
            createEmployeeRequestDTO.address()
        );
        return "redirect:/employees";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    public record CreateEmployeeRequestDTO(String name, String email, String phone, String address) {

    }
}
