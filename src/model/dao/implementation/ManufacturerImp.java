package model.dao.implementation;

import db.DB;
import db.exception.DBException;
import model.dao.ManufacturerDAO;
import model.entites.Manufacturer;

import java.sql.*;

public class ManufacturerImp implements ManufacturerDAO {

    private Connection connection;

    public ManufacturerImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Manufacturer manufacturer) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO manufacturer "
                    +"(Name) "
                    +"VALUES "
                    +"(?)",Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, manufacturer.getName());

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    manufacturer.setId(id);
                } else {
                    throw new DBException("Unexpected error! No rows affected");
                }
                DB.closeResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public void update(Manufacturer manufacturer) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void findById(Integer id) {

    }
}
