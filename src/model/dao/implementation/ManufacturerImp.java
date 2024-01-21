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
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE manufacturer "
                    +"SET Name = ? "
                    +"WHERE Id = ?");

            preparedStatement.setString(1, manufacturer.getName());

            preparedStatement.setInt(2, manufacturer.getId());

            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }

    }

    private Manufacturer manufacturerCreate (ResultSet resultSet)  throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt("Id"));
        manufacturer.setName(resultSet.getString("Name"));
        return manufacturer;
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM manufacturer "
                    +"WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Manufacturer findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT Id, Name FROM manufacturer "
                    +"WHERE id = ?", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return manufacturerCreate(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }
}
