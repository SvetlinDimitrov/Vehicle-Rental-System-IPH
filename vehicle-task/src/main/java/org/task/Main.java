package org.task;

import org.task.domain.enums.CliBadMessages;
import org.task.domain.enums.CliWelcomeMessages;
import org.task.domain.enums.VehicleTypes;
import org.task.domain.vehicle.Car;
import org.task.domain.vehicle.Motorcycle;
import org.task.domain.vehicle.Van;
import org.task.exceptions.VehicleException;
import org.task.processor.CarRentingProcessor;
import org.task.processor.MotorcycleRentingProcessor;
import org.task.processor.VanRentingProcessor;

import java.time.LocalDate;

public class Main {

  public static void main(String[] args) {
    try {
      System.out.println(CliWelcomeMessages.WELCOME.getMessage());
      String username = CliInputHandler.usernameInput();
      String vehicleType = CliInputHandler.vehicleTypeInput();
      String vehicleBrand = CliInputHandler.vehicleBrandInput();
      String vehicleModel = CliInputHandler.vehicleModelInput();
      double vehicleValue = Double.parseDouble(CliInputHandler.vehiclePriceInput());
      LocalDate localDate = LocalDate.parse(CliInputHandler.rentalDateInput());

      int rentalDays = Integer.parseInt(CliInputHandler.rentalDaysInput());
      int actualRentalDays = Integer.parseInt(CliInputHandler.actualRentalDaysInput());

      if (vehicleType.equals(VehicleTypes.Car.name())) {
        int safetyRating = Integer.parseInt(CliInputHandler.safetyRatingInput());
        Car car = new Car(vehicleBrand, vehicleModel, vehicleValue, safetyRating);
        System.out.println(CliWelcomeMessages.CAR_CREATED.getMessage());
        System.out.println();

        CarRentingProcessor processor = new CarRentingProcessor(username, rentalDays, actualRentalDays, car, localDate);
        processor.processRenting();
      } else if (vehicleType.equals(VehicleTypes.Van.name())) {
        int driverExperience = Integer.parseInt(CliInputHandler.driverExperienceInput());
        Van van = new Van(vehicleBrand, vehicleModel, vehicleValue, driverExperience);
        System.out.println(CliWelcomeMessages.VAN_CREATED.getMessage());
        System.out.println();

        VanRentingProcessor vanProcessor = new VanRentingProcessor(username, rentalDays, actualRentalDays, van, localDate);
        vanProcessor.processRenting();
      } else if (vehicleType.equals(VehicleTypes.Motorcycle.name())) {
        int riderAge = Integer.parseInt(CliInputHandler.riderAgeInput());
        Motorcycle motorcycle = new Motorcycle(vehicleBrand, vehicleModel, vehicleValue, riderAge);
        System.out.println(CliWelcomeMessages.MOTORCYCLE_CREATED.getMessage());
        System.out.println();

        MotorcycleRentingProcessor motorcycleProcessor = new MotorcycleRentingProcessor(username, rentalDays, actualRentalDays, motorcycle, localDate);
        motorcycleProcessor.processRenting();
      } else {
        throw new VehicleException(CliBadMessages.INVALID_VEHICLE_TYPE.getMessage());
      }
    } catch (VehicleException e) {
      System.out.println(e.getMessage());
    }
  }
}
