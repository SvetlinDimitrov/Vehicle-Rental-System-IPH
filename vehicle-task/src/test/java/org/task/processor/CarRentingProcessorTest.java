package org.task.processor;

import org.junit.jupiter.api.Test;
import org.task.domain.vehicle.Car;
import org.task.exceptions.VehicleException;

import java.time.LocalDate;

class CarRentingProcessorTest {

  @Test
  public void mainTestFromTheDocument() throws VehicleException {
    String vehicleBrand = "Mitsubishi";
    String vehicleModel = "Mirage";
    String username = "John Doe";
    double vehicleValue = 15000.00;
    int safetyRating = 3;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 10;
    int actualRentalDays = 10;
    Car car = new Car(vehicleBrand, vehicleModel, vehicleValue, safetyRating);
    CarRentingProcessor processor = new CarRentingProcessor(username, reservedRentalDays, actualRentalDays, car, date);
    processor.processRenting();
  }

  @Test
  public void testWithDiscount() throws VehicleException {
    String vehicleBrand = "Mitsubishi";
    String vehicleModel = "Mirage";
    String username = "John Doe";
    double vehicleValue = 15000.00;
    int safetyRating = 4;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 10;
    int actualRentalDays = 10;
    Car car = new Car(vehicleBrand, vehicleModel, vehicleValue, safetyRating);
    CarRentingProcessor processor = new CarRentingProcessor(username, reservedRentalDays, actualRentalDays, car, date);
    processor.processRenting();
  }

  @Test
  public void testWithEarlyReturn() throws VehicleException {
    String vehicleBrand = "Mitsubishi";
    String vehicleModel = "Mirage";
    String username = "John Doe";
    double vehicleValue = 15000.00;
    int safetyRating = 3;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 10;
    int actualRentalDays = 8;
    Car car = new Car(vehicleBrand, vehicleModel, vehicleValue, safetyRating);
    CarRentingProcessor processor = new CarRentingProcessor(username, reservedRentalDays, actualRentalDays, car, date);
    processor.processRenting();
  }

  @Test
  public void testWithEarlyReturnAndDiscount() throws VehicleException {
    String vehicleBrand = "Mitsubishi";
    String vehicleModel = "Mirage";
    String username = "John Doe";
    double vehicleValue = 15000.00;
    int safetyRating = 4;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 10;
    int actualRentalDays = 8;
    Car car = new Car(vehicleBrand, vehicleModel, vehicleValue, safetyRating);
    CarRentingProcessor processor = new CarRentingProcessor(username, reservedRentalDays, actualRentalDays, car, date);
    processor.processRenting();
  }

}