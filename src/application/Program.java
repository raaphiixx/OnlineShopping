package application;

import db.DB;
import model.dao.DAOFactory;
import model.dao.ManufacturerDAO;
import model.dao.implementation.ManufacturerImp;
import model.entites.Car;
import model.entites.Manufacturer;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Year;

public class Program {
    public static void main(String[] args) {
        System.out.println("HELLO WORLD!");

        Connection connection = DB.startConnection();

        ManufacturerDAO manufacturerDAO = DAOFactory.createManufacturerDao();

        /*
        System.out.println(">> First Test: insert a Manufacturer <<");
        Manufacturer manufacturer = new Manufacturer(null, "BMW");
        manufacturerDAO.insert(manufacturer);
        System.out.println(manufacturer.getId());
         */

        System.out.println(">> Second Test: findById a Manufacturer <<");
        Manufacturer manufacturer = manufacturerDAO.findById(1);
        System.out.println(manufacturer);



        DB.closeConnection();
    }
}
