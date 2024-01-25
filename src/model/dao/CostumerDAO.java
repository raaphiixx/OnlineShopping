package model.dao;

import model.entites.Car;
import model.entites.Costumer;

import java.util.List;

public interface CostumerDAO {
    void insert(Costumer costumer);
    void deleteById(Integer id);
    void update(Costumer costumer);
    Costumer findById(Integer id);
    List<Costumer> findAll();
    void buy(Car car);
}
