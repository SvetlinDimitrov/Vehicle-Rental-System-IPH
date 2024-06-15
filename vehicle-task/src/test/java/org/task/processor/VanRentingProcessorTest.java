package org.task.processor;

import org.junit.jupiter.api.Test;
import org.task.domain.vehicle.Van;
import org.task.exceptions.VehicleException;

import java.time.LocalDate;

class VanRentingProcessorTest {

  @Test
  public void mainTestFromTheDocument() throws VehicleException {
    String vehicleBrand = "Citroen";
    String vehicleModel = "Jumper";
    String username = "John Markson";
    double vehicleValue = 20000.00;
    int driverExperience = 8;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 15;
    int actualRentalDays = 10;
    Van van = new Van(vehicleBrand, vehicleModel, vehicleValue, driverExperience);
    VanRentingProcessor processor = new VanRentingProcessor(username, reservedRentalDays, actualRentalDays, van, date);
    processor.processRenting();
  }

  @Test
  public void testWithNoDiscountAndWithEarlyReturn() throws VehicleException {
    String vehicleBrand = "Citroen";
    String vehicleModel = "Jumper";
    String username = "John Markson";
    double vehicleValue = 20000.00;
    int driverExperience = 4;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 15;
    int actualRentalDays = 10;
    Van van = new Van(vehicleBrand, vehicleModel, vehicleValue, driverExperience);
    VanRentingProcessor processor = new VanRentingProcessor(username, reservedRentalDays, actualRentalDays, van, date);
    processor.processRenting();
  }

  @Test
  public void testWithNoDiscountAndNoEarlyReturn() throws VehicleException {
    String vehicleBrand = "Citroen";
    String vehicleModel = "Jumper";
    String username = "John Markson";
    double vehicleValue = 20000.00;
    int driverExperience = 4;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 15;
    int actualRentalDays = 15;
    Van van = new Van(vehicleBrand, vehicleModel, vehicleValue, driverExperience);
    VanRentingProcessor processor = new VanRentingProcessor(username, reservedRentalDays, actualRentalDays, van, date);
    processor.processRenting();
  }

  @Test
  public void testWithDiscountAndNoEarlyReturn() throws VehicleException {
    String vehicleBrand = "Citroen";
    String vehicleModel = "Jumper";
    String username = "John Markson";
    double vehicleValue = 20000.00;
    int driverExperience = 8;

    LocalDate date = LocalDate.parse("2024-06-03");
    int reservedRentalDays = 15;
    int actualRentalDays = 15;
    Van van = new Van(vehicleBrand, vehicleModel, vehicleValue, driverExperience);
    VanRentingProcessor processor = new VanRentingProcessor(username, reservedRentalDays, actualRentalDays, van, date);
    processor.processRenting();
  }
}