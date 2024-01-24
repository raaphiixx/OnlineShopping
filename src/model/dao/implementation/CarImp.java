package model.dao.implementation;

import db.DB;
import db.exception.DBException;
import model.dao.CarDAO;
import model.entites.Car;
import model.entites.Manufacturer;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class CarImp implements CarDAO {

    Connection connection = null;

    public CarImp(Connection connection) {
        this.connection = connection;
    }

    private Car createCar(ResultSet resultSet, Manufacturer manufacturer) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("Id"));
        car.setModel(resultSet.getString("Model"));
        car.setYear(Year.of(resultSet.getInt("Year")));
        car.setPrice(resultSet.getDouble("Price"));
        car.setColor(resultSet.getString("Color"));
        car.setStock(resultSet.getInt("Stock"));
        car.setManufacturer(manufacturer);
        return car;
    }

    private Manufacturer manufacturerCreate (ResultSet resultSet)  throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt("Id"));
        manufacturer.setName(resultSet.getString("ManName"));
        return manufacturer;
    }



    @Override
    public void insert(Car car) {
        PreparedStatement preparedStatement = null;
        Year year = car.getYear();
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO car "
                    +"(Model, Year, Price, Color, Stock, ManufacturerID) "
                    +"VALUES "
                    +"(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, year.getValue());
            preparedStatement.setDouble(3, car.getPrice());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setInt(5, car.getStock());
            preparedStatement.setInt(6, car.getManufacturer().getId());

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    car.setId(id);
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
    public Car findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT car.*, manufacturer.Name as ManName "
                    +"FROM car INNER JOIN manufacturer "
                    +"ON car.ManufacturerId = manufacturer.Id "
                    +"WHERE car.Id = ?",Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Manufacturer manufacturer = manufacturerCreate(resultSet);
                return createCar(resultSet, manufacturer);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Car> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Car> list = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT car.*, manufacturer.Name as ManName "
                    +"FROM car JOIN manufacturer "
                    +"ON car.ManufacturerID = manufacturer.Id "
                    +"ORDER BY Model");

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Manufacturer manufacturer = manufacturerCreate(resultSet);
                Car car = createCar(resultSet, manufacturer);
                list.add(car);
            }
            return list;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM car "
                    +"WHERE Id = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Car car) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE car "
                    +"SET Model = ?, Year = ?, Price = ?, "
                    +"Color = ?, Stock = ?, ManufacturerId = ? "
                    +"WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getYear().getValue());
            preparedStatement.setDouble(3, car.getPrice());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setInt(5, car.getStock());
            preparedStatement.setInt(6, car.getManufacturer().getId());
            preparedStatement.setInt(7, car.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }
}
