package model.dao;

import model.entites.Manufacturer;

import java.util.List;

public interface ManufacturerDAO {
    void insert(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
    void deleteById(Integer id);
    Manufacturer findById(Integer id);
    List<Manufacturer> findAll();
}
