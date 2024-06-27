package gift.infra;

import gift.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> getCustomers() {
        String sql = "SELECT * FROM Customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    public Customer getCustomerById(long id) {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerRowMapper());
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (id, firstName, lastName) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, customer.id(), customer.firstName(), customer.lastName());
    }

    public void deleteCustomer(long id) {
        String sql = "DELETE FROM Customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateCustomer(long id, Customer customer) {
        String sql = "UPDATE Customer SET firstName = ?, lastName = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.firstName(), customer.lastName(), id);
    }
}
