package model.dao;

import db.DB;
import model.dao.implementation.CarImp;
import model.dao.implementation.ManufacturerImp;

public class DAOFactory {

    public static ManufacturerDAO createManufacturerDao() {
        return new ManufacturerImp(DB.startConnection());
    }

    public static CarDAO createCarDao() {
        return new CarImp(DB.startConnection());
    }
}
