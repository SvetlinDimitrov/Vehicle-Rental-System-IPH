package org.task.domain.vehicle;

import org.task.exceptions.VehicleException;

public class Van extends Vehicle {

  private final int driverExperience;

  public Van(String brand, String model, Double value, int driverExperience) throws VehicleException {
    super(brand, model, value);
    if (driverExperience < 0) {
      throw new VehicleException("Driver experience can't be less than 0");
    }
    this.driverExperience = driverExperience;
  }

  public int getDriverExperience() {
    return driverExperience;
  }
}
