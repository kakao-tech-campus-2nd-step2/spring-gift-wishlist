package gift.infra;

import gift.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getCustomers() {
        String sql = "SELECT * FROM Customer";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public Employee getCustomerById(long id) {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
    }

    public void addCustomer(Employee customer) {
        String sql = "INSERT INTO Customer (id, name, address, phone, email) VALUES (?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, customer.id(), customer.name(), customer.address(),
            customer.phone(), customer.email());
    }

    public void deleteCustomer(long id) {
        String sql = "DELETE FROM Customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateCustomer(long id, Employee customer) {
        String sql = "UPDATE Customer SET name=? WHERE id = ?";
        jdbcTemplate.update(sql, customer.name(), id);
    }
}
