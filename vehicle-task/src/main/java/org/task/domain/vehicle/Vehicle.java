package org.task.domain.vehicle;

import org.task.exceptions.VehicleException;

public abstract class Vehicle {

  protected final String brand;
  protected final String model;
  protected final Double value;

  public Vehicle(String brand, String model, Double value) throws VehicleException {
    if (brand == null || brand.isBlank() || brand.isEmpty()) {
      throw new VehicleException("Vehicle brand cannot be null, empty or blank");
    }
    if (model == null || model.isBlank() || model.isEmpty()) {
      throw new VehicleException("Vehicle model cannot be null, empty or blank");
    }
    if (value == null || value < 0) {
      throw new VehicleException("Vehicle price cannot be null or negative");
    }
    this.brand = brand;
    this.model = model;
    this.value = value;
  }

  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public Double getValue() {
    return value;
  }


}
