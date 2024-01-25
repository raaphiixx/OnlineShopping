package application;

import db.DB;
import model.dao.CarDAO;
import model.dao.CostumerDAO;
import model.dao.DAOFactory;
import model.dao.ManufacturerDAO;
import model.entites.Car;
import model.entites.Costumer;
import model.entites.Manufacturer;

import java.sql.Connection;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        System.out.println("HELLO WORLD!");

        Connection connection = DB.startConnection();

        ManufacturerDAO manufacturerDAO = DAOFactory.createManufacturerDao();
        CarDAO carDAO = DAOFactory.createCarDao();
        CostumerDAO costumerDAO = DAOFactory.createCostumerDao();

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

        /*
        System.out.println(">> Car Second Test: findById <<");

        Car car = carDAO.findById(1);
        System.out.println(car);
        */

        /*
        System.out.println(">> Car Third Test: deleteById <<");
        carDAO.deleteById(1);
        */

        /*
        System.out.println(">> Car Forth Test: update <<");
        Manufacturer manufacturer = manufacturerDAO.findById(1);
        Car car = carDAO.findById(2);
        car.setModel("Continental GT S");
        car.setManufacturer(manufacturer);
        car.setPrice(200000.00);
        carDAO.update(car);
        */

        /*
        System.out.println(">> Car Fifth Test: findAll <<");
        List<Car> list = carDAO.findAll();

        for (Car cars : list) {
            System.out.println(cars);
        }
         */

        /*
        System.out.println(">> Costumer First test: deleteById <<");
        costumerDAO.deleteById(1);
        */

        /*
        System.out.println(">> Costumer Second test: insert <<");
        Costumer costumer = new Costumer(null, "Daniel", "Ruby");
        costumerDAO.insert(costumer);
        System.out.println(costumer);
        */

        /*
        System.out.println(">> Costumer Third Test: findById <<");
        System.out.println(costumerDAO.findById(9));
        */

        /*
        System.out.println(">> Costumer Forth Test: findAll <<");
        List<Costumer> costumers = costumerDAO.findAll();
        for(Costumer costumer : costumers) {
            System.out.println(costumer);
        }
        */

        /*
        System.out.println(">> Costumer Fifth Test: update <<");
        Costumer costumer = costumerDAO.findById(3);
        System.out.println("OLD NAME: ");
        System.out.println(costumer);

        costumer.setfName("Luisa");
        costumer.setlName("Yellow");
        costumerDAO.update(costumer);
        System.out.println("NEW NAME: ");
        System.out.println(costumerDAO.findById(3));
        */

        /*
        System.out.println(">> Costumer Sixth Test: buy <<");
        System.out.println("BEFORE: ");
        Car car = carDAO.findById(2);
        System.out.println(car);

        System.out.println("AFTER: ");
        costumerDAO.buy(car);
        System.out.println(carDAO.findById(2));
        */

        DB.closeConnection();
    }
}
