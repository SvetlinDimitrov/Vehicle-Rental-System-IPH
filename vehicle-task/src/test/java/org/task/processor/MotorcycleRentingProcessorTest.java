package org.task.processor;

import org.junit.jupiter.api.Test;
import org.task.domain.vehicle.Motorcycle;
import org.task.exceptions.VehicleException;

import java.time.LocalDate;

class MotorcycleRentingProcessorTest {


  @Test
  public void mainTestFromTheDocument() throws VehicleException {
    String vehicleBrand = "Triumph";
    String vehicleModel = "Tiger Sport 660";
    String username = "Mary Johnson";
    double vehicleValue = 10000.00;
    int riderAge = 20;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 10;
    int actualRentalDays = 10;
    Motorcycle motorcycle = new Motorcycle(vehicleBrand, vehicleModel, vehicleValue, riderAge);
    MotorcycleRentingProcessor processor = new MotorcycleRentingProcessor(username, reservedRentalDays, actualRentalDays, motorcycle, date);
    processor.processRenting();
  }

  @Test
  public void testWithoutTheDiscount() throws VehicleException {
    String vehicleBrand = "Triumph";
    String vehicleModel = "Tiger Sport 660";
    String username = "Mary Johnson";
    double vehicleValue = 10000.00;
    int riderAge = 25;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 10;
    int actualRentalDays = 10;
    Motorcycle motorcycle = new Motorcycle(vehicleBrand, vehicleModel, vehicleValue, riderAge);
    MotorcycleRentingProcessor processor = new MotorcycleRentingProcessor(username, reservedRentalDays, actualRentalDays, motorcycle, date);
    processor.processRenting();
  }

  @Test
  public void testWithEarlyReturn() throws VehicleException {
    String vehicleBrand = "Triumph";
    String vehicleModel = "Tiger Sport 660";
    String username = "Mary Johnson";
    double vehicleValue = 10000.00;
    int riderAge = 25;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 10;
    int actualRentalDays = 5;
    Motorcycle motorcycle = new Motorcycle(vehicleBrand, vehicleModel, vehicleValue, riderAge);
    MotorcycleRentingProcessor processor = new MotorcycleRentingProcessor(username, reservedRentalDays, actualRentalDays, motorcycle, date);
    processor.processRenting();
  }

  @Test
  public void testWithEarlyReturnAndDiscount() throws VehicleException {
    String vehicleBrand = "Triumph";
    String vehicleModel = "Tiger Sport 660";
    String username = "Mary Johnson";
    double vehicleValue = 10000.00;
    int riderAge = 20;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 10;
    int actualRentalDays = 5;
    Motorcycle motorcycle = new Motorcycle(vehicleBrand, vehicleModel, vehicleValue, riderAge);
    MotorcycleRentingProcessor processor = new MotorcycleRentingProcessor(username, reservedRentalDays, actualRentalDays, motorcycle, date);
    processor.processRenting();
  }

}