package gift.model.dao;

import gift.model.Product;
import gift.model.repository.ProductRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCProductDao implements ProductRepository {
    private static final String URL = "jdbc:h2:mem:test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public JDBCProductDao(){
        createTable();
    }

    @Override
    public void save(Product entity) {
        if(entity.isNew()){
            try{
                Connection connection = getConnection();
                PreparedStatement state = connection.prepareStatement(ProductQuery.INSERT_PRODUCT.getQuery());
                state.setString(1, entity.getName());
                state.setInt(2, entity.getPrice());
                state.setString(3, entity.getImgUrl());
                state.setBoolean(4, entity.isDeleted());
                state.execute();
                state.close();
                connection.close();
            } catch (SQLException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
            return;
        }
        update(entity);
    }

    public void update(Product entity){
        try{
            Connection connection = getConnection();
            PreparedStatement state = connection.prepareStatement(ProductQuery.UPDATE_PRODUCT.getQuery());
            state.setString(1, entity.getName());
            state.setInt(2, entity.getPrice());
            state.setString(3, entity.getImgUrl());
            state.setBoolean(4, entity.isDeleted());
            state.setLong(5, entity.getId());
            state.execute();
            state.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> find(Long id) {
        Product product = null;
        try{
            Connection connection = getConnection();
            PreparedStatement state = connection.prepareStatement(ProductQuery.SELECT_PRODUCT_BY_ID.getQuery());
            state.setLong(1, id);
            state.execute();
            if (state.getResultSet().next()){
                product = new Product(
                    state.getResultSet().getLong("id"),
                    state.getResultSet().getString("name"),
                    state.getResultSet().getInt("price"),
                    state.getResultSet().getString("image_url"),
                    state.getResultSet().getBoolean("is_deleted")
                );
            }
            state.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return Optional.ofNullable(product);
    }

    @Override
    public void delete(Product entity) {
        try{
            Connection connection = getConnection();
            PreparedStatement state = connection.prepareStatement(ProductQuery.DELETE_PRODUCT.getQuery());
            state.setLong(1, entity.getId());
            state.execute();
            state.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try{
            Connection connection = getConnection();
            Statement state = connection.createStatement();
            state.execute(ProductQuery.SELECT_ALL_PRODUCTS.getQuery());
            while (state.getResultSet().next()){
                products.add(new Product(
                    state.getResultSet().getLong("id"),
                    state.getResultSet().getString("name"),
                    state.getResultSet().getInt("price"),
                    state.getResultSet().getString("image_url"),
                    state.getResultSet().getBoolean("is_deleted")
                ));
            }
            state.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return products;
    }

    private Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void createTable() {
        try{
            Connection connection = getConnection();
            Statement state = connection.createStatement();
            state.execute(ProductQuery.CREATE_PRODUCT_TABLE.getQuery());
            connection.commit();
            state.close();
            connection.close();
            System.out.println("Table created");
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
