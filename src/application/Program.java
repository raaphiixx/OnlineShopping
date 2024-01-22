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
import java.util.List;

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

        /*
        System.out.println(">> Second Test: findById a Manufacturer <<");
        Manufacturer manufacturer = manufacturerDAO.findById(1);
        System.out.println(manufacturer);
         */

        /*
        System.out.println(">> Third Test: deleteById a Manufacturer");
        manufacturerDAO.deleteById(2);
         */

        /*
        System.out.println(">> Fourth Test: deleteById a Manufacturer <<");
        Manufacturer manufacturer = manufacturerDAO.findById(1);
        manufacturer.setName("Bentley");
        manufacturerDAO.update(manufacturer);
        System.out.println(manufacturer);
         */

        /*
        System.out.println(">> Fifth Test: findAll in Manufacturer <<");
        List<Manufacturer> list = manufacturerDAO.findAll();

        for(Manufacturer manufacturer : list) {
            System.out.println(manufacturer);
        }
         */


        DB.closeConnection();
    }
}
