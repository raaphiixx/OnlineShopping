package model.dao;

import model.entites.Manufacturer;

public interface ManufacturerDAO {
    void insert(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
    void deleteById(Integer id);
    void findById(Integer id);

}
