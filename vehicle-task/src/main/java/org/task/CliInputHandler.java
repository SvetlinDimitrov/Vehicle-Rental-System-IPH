package org.task;

import org.task.domain.enums.CliBadMessages;
import org.task.domain.enums.CliWelcomeMessages;
import org.task.domain.enums.VehicleTypes;
import org.task.exceptions.VehicleException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class CliInputHandler {

  private final static Scanner scanner = new Scanner(System.in);

  public static String usernameInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_USERNAME.getMessage());
      String username = scanner.nextLine();
      if (username.isEmpty() || username.isBlank())
        throw new VehicleException(CliBadMessages.INVALID_USERNAME.getMessage());
      return username;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return usernameInput();
    }
  }

  public static String vehicleTypeInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_VEHICLE_TYPE.getMessage());
      String vehicleType = scanner.nextLine();
      if (Arrays.stream(VehicleTypes.values()).map(Enum::name).filter(a -> a.equals(vehicleType)).findAny().isEmpty())
        throw new VehicleException(CliBadMessages.INVALID_VEHICLE_TYPE.getMessage());
      return vehicleType;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return vehicleTypeInput();
    }
  }

  public static String vehicleBrandInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_VEHICLE_BRAND.getMessage());
      String vehicleBrand = scanner.nextLine();
      if (vehicleBrand.isEmpty() || vehicleBrand.isBlank())
        throw new VehicleException(CliBadMessages.INVALID_VEHICLE_BRAND.getMessage());
      return vehicleBrand;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return vehicleBrandInput();
    }
  }

  public static String vehicleModelInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_VEHICLE_MODEL.getMessage());
      String vehicleModel = scanner.nextLine();
      if (vehicleModel.isEmpty() || vehicleModel.isBlank())
        throw new VehicleException(CliBadMessages.INVALID_VEHICLE_MODEL.getMessage());
      return vehicleModel;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return vehicleModelInput();
    }
  }

  public static String vehiclePriceInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_VEHICLE_VALUE.getMessage());
      String input = scanner.nextLine();
      if (input.isEmpty() || input.isBlank())
        throw new VehicleException(CliBadMessages.INVALID_VEHICLE_VALUE.getMessage());
      Double.parseDouble(input);
      return input;
    } catch (Exception e) {
      System.out.println(CliBadMessages.INVALID_VEHICLE_VALUE.getMessage());
      return vehiclePriceInput();
    }
  }

  public static String rentalDateInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_LOCAL_DATE.getMessage());
      String input = scanner.nextLine();
      if (input.isEmpty() || input.isBlank())
        throw new VehicleException(CliBadMessages.INVALID_LOCAL_DATE.getMessage());
      LocalDate.parse(input);
      return input;
    } catch (Exception e) {
      System.out.println(CliBadMessages.INVALID_LOCAL_DATE.getMessage());
      return rentalDateInput();
    }
  }

  public static String rentalDaysInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_RENTAL_DAYS.getMessage());
      String input = scanner.nextLine();
      if (input.isEmpty() || input.isBlank() || Integer.parseInt(input) < 0)
        throw new VehicleException(CliBadMessages.INVALID_RENTAL_DAYS.getMessage());
      return input;
    } catch (Exception e) {
      System.out.println(CliBadMessages.INVALID_RENTAL_DAYS.getMessage());
      return rentalDaysInput();
    }
  }

  public static String actualRentalDaysInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_ACTUAL_RENTAL_DAYS.getMessage());
      String input = scanner.nextLine();
      if (input.isEmpty() || input.isBlank() || Integer.parseInt(input) < 0)
        throw new VehicleException(CliBadMessages.INVALID_ACTUAL_RENTAL_DAYS.getMessage());
      return input;
    } catch (Exception e) {
      System.out.println(CliBadMessages.INVALID_ACTUAL_RENTAL_DAYS.getMessage());
      return actualRentalDaysInput();
    }
  }

  public static String safetyRatingInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_SAFETY_RATING.getMessage());
      String input = scanner.nextLine();
      if (input.isEmpty() || input.isBlank() || Integer.parseInt(input) < 0 || Integer.parseInt(input) > 5)
        throw new VehicleException(CliBadMessages.INVALID_SAFETY_RATING.getMessage());
      return input;
    } catch (Exception e) {
      System.out.println(CliBadMessages.INVALID_SAFETY_RATING.getMessage());
      return safetyRatingInput();
    }
  }

  public static String driverExperienceInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_DRIVER_EXPERIENCE.getMessage());
      String input = scanner.nextLine();
      if (input.isEmpty() || input.isBlank() || Integer.parseInt(input) < 0)
        throw new VehicleException(CliBadMessages.INVALID_DRIVER_EXPERIENCE.getMessage());
      return input;
    } catch (Exception e) {
      System.out.println(CliBadMessages.INVALID_DRIVER_EXPERIENCE.getMessage());
      return driverExperienceInput();
    }
  }

  public static String riderAgeInput() {
    try {
      System.out.print(CliWelcomeMessages.ENTER_RIDER_AGE.getMessage());
      String input = scanner.nextLine();
      if (input.isEmpty() || input.isBlank() || Integer.parseInt(input) < 0)
        throw new VehicleException(CliBadMessages.INVALID_RIDER_AGE.getMessage());
      return input;
    } catch (Exception e) {
      System.out.println(CliBadMessages.INVALID_RIDER_AGE.getMessage());
      return riderAgeInput();
    }
  }
}
