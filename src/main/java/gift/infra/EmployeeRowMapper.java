package gift.infra;

import gift.domain.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("address"),
            rs.getString("phone")

        );
    }
}
