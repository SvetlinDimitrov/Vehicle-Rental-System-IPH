package org.task.domain;

import org.task.exceptions.VehicleException;

public class Car extends Vehicle {

  private final Integer safetyRating;

  public Car(String brand, String model, double value, Integer safetyRating) throws VehicleException {
    super(brand, model, value);
    if (safetyRating < 0 || safetyRating > 5) {
      throw new VehicleException("Safety rating must be between 0 and 5");
    }
    this.safetyRating = safetyRating;
  }

  public Integer getSafetyRating() {
    return safetyRating;
  }

  public Boolean getIsHighSafetyRating() {
    return safetyRating >= 4;
  }
}
