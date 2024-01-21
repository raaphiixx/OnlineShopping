package model.dao;

import db.DB;
import model.dao.implementation.ManufacturerImp;

public class DAOFactory {

    public static ManufacturerDAO createManufacturerDao() {
        return new ManufacturerImp(DB.startConnection());
    }
}
