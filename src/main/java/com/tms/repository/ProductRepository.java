package com.tms.repository;

import com.tms.config.DatabaseService;
import com.tms.config.SQLQuery;
import com.tms.model.Product;
import com.tms.model.User;
import com.tms.model.dto.ProductRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tms.config.SQLQuery.*;

@Repository
public class ProductRepository {

    private final DatabaseService databaseService;

    @Autowired
    public ProductRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Product> getProductById(Long id) {
        Connection connection = databaseService.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseProduct(resultSet);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Long> createProduct(ProductRequestDto productRequestDto) {
        Connection connection = databaseService.getConnection();
        Long productId = null;

        try {
            PreparedStatement createProductStatement = connection.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            createProductStatement.setString(1, productRequestDto.getName());
            createProductStatement.setDouble(2, productRequestDto.getPrice());
            createProductStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            createProductStatement.executeUpdate();

            ResultSet generatedKeys = createProductStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                productId = generatedKeys.getLong(1);
            }
            return Optional.of(productId);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean updateProduct(ProductRequestDto productRequestDto) {
        Connection connection = databaseService.getConnection();

        try {
            PreparedStatement getProductStatement = connection.prepareStatement(UPDATE_PRODUCT);
            getProductStatement.setString(1, productRequestDto.getName());
            getProductStatement.setDouble(2, productRequestDto.getPrice());
            getProductStatement.setLong(3, productRequestDto.getId());
            return getProductStatement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean deleteProduct(Long id) {
        Connection connection = databaseService.getConnection();
        try{
            PreparedStatement getProductStatement = connection.prepareStatement(DELETE_PRODUCT);
            getProductStatement.setLong(1, id);
            return getProductStatement.executeUpdate() > 1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Product> getAllProducts() {
        Connection connection = databaseService.getConnection();

        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement getAllUsersStatement = connection.prepareStatement(SQLQuery.GET_ALL_PRODUCTS);
            ResultSet resultSet = getAllUsersStatement.executeQuery();
            while (resultSet.next()) {
                products.add(parseProductGetAllProducts(resultSet));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    private Optional<Product> parseProduct(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            product.setCreated(resultSet.getTimestamp("created"));
            product.setUpdated(resultSet.getTimestamp("updated"));
            return Optional.of(product);
        }
        return Optional.empty();
    }

    private Product parseProductGetAllProducts(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            product.setCreated(resultSet.getTimestamp("created"));
            product.setUpdated(resultSet.getTimestamp("updated"));
            return product;
        }
        return null;
    }
}


