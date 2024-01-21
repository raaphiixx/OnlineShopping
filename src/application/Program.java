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

        System.out.println(">> First Test: Insert a Manufacturer <<");
        Manufacturer manufacturer = new Manufacturer(null, "BMW");

        manufacturerDAO.insert(manufacturer);

        System.out.println(manufacturer.getId());

        DB.closeConnection();
    }
}
