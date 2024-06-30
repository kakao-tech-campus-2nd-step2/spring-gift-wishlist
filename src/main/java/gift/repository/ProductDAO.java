package gift.repository;

import gift.model.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class ProductDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private long idTraker = 1;

    public ProductRecord[] getAllRecords() {
        String sql = "select * from products";

        return jdbcTemplate.query(sql, (record,rowNum) -> new ProductRecord(
                record.getLong("id"),
                record.getString("name"),
                record.getInt("price"),
                record.getString("imageUrl")
                )
            ).toArray(new ProductRecord[0]);
    }

    public ProductRecord getRecord(long id) {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException();
        }

        String sql = "select * from products where id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new ProductRecord(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
        ), id);
    }

    public ProductRecord addNewRecord(ProductRecord product) {
        return addNewRecord(product, getNewId());
    }

    public ProductRecord addNewRecord(ProductRecord product, long id) throws DuplicateKeyException {
        if (isRecordExist(id)) {
            throw new DuplicateKeyException("A record with the given ID already exists.");
        }

        ProductRecord record = product.withId(id);

        String sql = "insert into products values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, record.id(), record.name(), record.price(), record.imageUrl());

        return record;
    }

    public ProductRecord replaceRecord(long id, ProductRecord product) throws NoSuchElementException {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException("Record not found");
        }

        ProductRecord record = product.withId(id);

        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, record.name(), record.price(), record.imageUrl(), record.id());

        return record;
    }

    public ProductRecord updateRecord(long id, ProductRecord patch) throws NoSuchElementException {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException("Record not found");
        }

        ProductRecord record = getRecord(id).getUpdatedRecord(patch);

        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, record.name(), record.price(), record.imageUrl(), record.id());

        return record;
    }

    public void deleteRecord(long id) throws NoSuchElementException {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException("Record not found");
        }

        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    public boolean isRecordExist(long id) {
        String sql = "select count(*) from products where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if (count > 0) {
            return true;
        }

        return false;
    }

    private long getNewId() {
        while (isRecordExist(idTraker)) {
            if (idTraker == Long.MAX_VALUE) {
                idTraker = 0;
            }
            idTraker++;
        }

        return idTraker;
    }
}
