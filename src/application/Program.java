package application;

import db.DB;
import model.dao.CarDAO;
import model.dao.DAOFactory;
import model.dao.ManufacturerDAO;
import model.entites.Car;
import model.entites.Manufacturer;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.time.Year;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        System.out.println("HELLO WORLD!");

        Connection connection = DB.startConnection();

        ManufacturerDAO manufacturerDAO = DAOFactory.createManufacturerDao();
        CarDAO carDAO = DAOFactory.createCarDao();

        /*
        System.out.println(">> Manufacturer First Test: insert <<");
        Manufacturer manufacturer = new Manufacturer(null, "BMW");
        manufacturerDAO.insert(manufacturer);
        System.out.println(manufacturer.getId());
         */


        /*
        System.out.println(">> Manufacturer Second Test: findById <<");
        Manufacturer manufacturer = manufacturerDAO.findById(1);
        System.out.println(manufacturer);
         */

        /*
        System.out.println(">> Manufacturer Third Test: deleteById");
        manufacturerDAO.deleteById(2);
        */

        /*
        System.out.println(">> Manufacturer Fourth Test: update <<");
        Manufacturer manufacturer = manufacturerDAO.findById(1);
        manufacturer.setName("Bentley");
        manufacturerDAO.update(manufacturer);
        System.out.println(manufacturer);
        */

        /*
        System.out.println(">> Manufacturer Fifth Test: findAll <<");
        List<Manufacturer> list = manufacturerDAO.findAll();

        for(Manufacturer manufacturer : list) {
            System.out.println(manufacturer);
        }
        */

        /*
        System.out.println(">> Car First Test: Insert <<");

        Manufacturer manufacturer = manufacturerDAO.findById(3);

        Car car = new Car(null, "X7", Year.parse("2018"), 25000.00, "Black", 20, manufacturer);

        carDAO.insert(car);
        System.out.println();
        System.out.println(car.getId());
        */

        System.out.println(">> Car Second Test: findById <<");

        Car car = carDAO.findById(1);
        System.out.println(car);

        DB.closeConnection();
    }
}
