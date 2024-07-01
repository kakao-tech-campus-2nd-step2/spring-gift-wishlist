package gift.infra;

import gift.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Employee> getEmployees() {
        String sql = "SELECT * FROM Employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public Employee getEmployeeById(long id) {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
    }

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employee (id, name, address, phone, email) VALUES (?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, employee.id(), employee.name(), employee.address(),
            employee.phone(), employee.email());
    }

    public void deleteEmployee(long id) {
        String sql = "DELETE FROM Employee WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateEmployee(long id, Employee employee) {
        String sql = "UPDATE Employee SET name=? WHERE id = ?";
        jdbcTemplate.update(sql, employee.name(), id);
    }
}
