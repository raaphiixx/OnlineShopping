package model.dao.implementation;

import db.DB;
import db.exception.DBException;
import model.dao.CarDAO;
import model.entites.Car;
import model.entites.Manufacturer;

import java.sql.*;
import java.time.Year;
import java.util.List;

public class CarImp implements CarDAO {

    Connection connection = null;

    public CarImp(Connection connection) {
        this.connection = connection;
    }

    private Car createCar(ResultSet resultSet, Manufacturer manufacturer) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("Id"));
        car.setYear(Year.of(resultSet.getInt("Year")));
        car.setPrice(resultSet.getDouble("Price"));
        car.setColor(resultSet.getString("Color"));
        car.setStock(resultSet.getInt("Stock"));
        car.setManufacturer(manufacturer);
        return car;
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
    public void deleteById(Integer id) {

    }

    @Override
    public void update(Car car) {

    }

    @Override
    public Car findById(Car id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }
}