package org.task.domain.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CliBadMessages {
  INVALID_USERNAME("Invalid username. Cannot be empty or blank!"),
  INVALID_VEHICLE_TYPE("Invalid vehicle type. Must be " + Arrays.stream(VehicleTypes.values()).map(Enum::name).collect(Collectors.joining(",")) + "!"),
  INVALID_VEHICLE_BRAND("Invalid vehicle brand. Cannot be empty or blank!"),
  INVALID_VEHICLE_MODEL("Invalid vehicle model. Cannot be empty or blank!"),
  INVALID_VEHICLE_VALUE("Invalid vehicle value. Must be a positive number!"),
  INVALID_LOCAL_DATE("Invalid rental date. Must be in the format yyyy-MM-dd!"),
  INVALID_RENTAL_DAYS("Invalid rental days. Must be a positive number!"),
  INVALID_ACTUAL_RENTAL_DAYS("Invalid actual rental days. Must be a positive number!"),
  INVALID_SAFETY_RATING("Invalid safety rating. Must be a positive number between 0 and 5!"),
  INVALID_DRIVER_EXPERIENCE("Invalid driver experience. Must be a positive number!"),
  INVALID_RIDER_AGE("Invalid rider age. Must be a positive number!");

  private final String message;

  CliBadMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
