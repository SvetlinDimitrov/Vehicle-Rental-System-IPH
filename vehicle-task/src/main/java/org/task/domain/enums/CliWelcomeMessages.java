package org.task.domain.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CliWelcomeMessages {

  WELCOME("Welcome to the vehicle rental system"),
  ENTER_USERNAME("Enter your username:"),
  ENTER_VEHICLE_TYPE("Enter the vehicle type (" + Arrays.stream(VehicleTypes.values()).map(Enum::name).collect(Collectors.joining(",")) + "):"),
  ENTER_VEHICLE_BRAND("Enter the vehicle brand:"),
  ENTER_VEHICLE_MODEL("Enter the vehicle model:"),
  ENTER_VEHICLE_VALUE("Enter the vehicle value:"),
  ENTER_LOCAL_DATE("Enter the rental date (yyyy-MM-dd):"),
  ENTER_RENTAL_DAYS("Enter the rental days:"),
  ENTER_SAFETY_RATING("Enter the safety rating of the car:"),
  CAR_CREATED("Car created successfully :)"),
  ENTER_DRIVER_EXPERIENCE("Please enter the number of years of driving experience:"),
  VAN_CREATED("Van created successfully :)"),
  ENTER_ACTUAL_RENTAL_DAYS("Enter the actual rental days:"),
  ENTER_RIDER_AGE("Please enter the age of the motorcycle rider:"),
  MOTORCYCLE_CREATED("Motorcycle created successfully :)");

  private final String message;

  CliWelcomeMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
