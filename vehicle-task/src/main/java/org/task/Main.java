package org.task;

import org.task.domain.Motorcycle;
import org.task.domain.VehicleTypes;
import org.task.exceptions.VehicleException;
import org.task.processor.MotorcycleRentingProcessor;

public class Main {

  public static void main(String[] args) throws VehicleException {

    for (VehicleTypes type : VehicleTypes.values()) {
      String vehicleType = type.name();
      if (vehicleType.equals(VehicleTypes.MOTORCYCLE.name())) {
        String vehicleBrand = "Triumph";
        String vehicleModel = "Tiger Sport 660";
        double vehicleValue = 10000.00;
        int riderAge = 20;
        int reservedRentalDays = 10;
        int actualRentalDays = 10;
        Motorcycle motorcycle = new Motorcycle(vehicleBrand, vehicleModel, vehicleValue, riderAge);
        MotorcycleRentingProcessor processor = new MotorcycleRentingProcessor(reservedRentalDays, actualRentalDays, motorcycle);
        System.out.println("\n");
        processor.processRenting();
      } else if (vehicleType.equals(VehicleTypes.CAR.name())) {
//        String vehicleBrand = "Mitsubishi";
//        String vehicleModel = "Mirage";
//        double vehicleValue = 15000.00;
//        int safetyRating = 4;
//        int reservedRentalDays = 10;
//        int actualRentalDays = 10;
//        Car car = new Car(vehicleBrand, vehicleModel, vehicleValue, safetyRating);
//        CarRentingProcessor processor = new CarRentingProcessor(car, reservedRentalDays, actualRentalDays);
//        processor.processRenting();
      } else if (vehicleType.equals(VehicleTypes.VAN.name())) {

      }
    }
    ;
  }
}
