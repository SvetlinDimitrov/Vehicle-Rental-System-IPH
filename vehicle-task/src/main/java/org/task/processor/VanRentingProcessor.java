package org.task.processor;

import org.task.exceptions.VehicleException;

public class VanRentingProcessor extends VehicleRentingProcessor{

  protected VanRentingProcessor(Integer reservedRentalDays, Integer actualRentalDays) throws VehicleException {
    super(reservedRentalDays, actualRentalDays);
  }

  @Override
  void processRenting() {

  }
}
