package org.task.domain;

import org.task.exceptions.VehicleException;

public class Motorcycle extends Vehicle {

  private final Integer riderAge;

  public Motorcycle(String brand, String model, Double value, Integer riderAge) throws VehicleException {
    super(brand, model, value);
    this.riderAge = riderAge;
  }

  public Integer getRiderAge() {
    return riderAge;
  }

}
