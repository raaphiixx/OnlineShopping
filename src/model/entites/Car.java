package model.entites;

import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;

public class Car {

    private Integer id;
    private String model;
    private Year year;
    private Double price;
    private String color;
    private Integer stock;
    private Manufacturer manufacturer;

    public Car() {
    }

    public Car(Integer id, String model, Year year, Double price, String color, Integer stock, Manufacturer manufacturer) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.stock = stock;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) && Objects.equals(year, car.year) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, year, color);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", stock=" + stock +
                '}';
    }
}
