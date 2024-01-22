package model.dao;

import model.entites.Car;
import model.entites.Manufacturer;

import java.util.List;

public interface CarDAO {
    void insert(Car car);
    void deleteById(Integer id);
    void update(Car car);
    Car findById(Car id);
    List<Car> findAll();
}
