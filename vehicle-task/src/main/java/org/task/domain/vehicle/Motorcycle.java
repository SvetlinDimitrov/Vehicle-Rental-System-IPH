package org.task.domain.vehicle;

import org.task.exceptions.VehicleException;

public class Motorcycle extends Vehicle {

  private final Integer riderAge;

  public Motorcycle(String brand, String model, Double value, Integer riderAge) throws VehicleException {
    super(brand, model, value);
    if (riderAge < 0) {
      throw new VehicleException("Rider age can't be less than 0");
    }
    this.riderAge = riderAge;
  }

  public Integer getRiderAge() {
    return riderAge;
  }

}
