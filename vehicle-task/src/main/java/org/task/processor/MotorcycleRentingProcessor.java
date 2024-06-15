package org.task.processor;

import org.task.domain.vehicle.Motorcycle;
import org.task.exceptions.VehicleException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MotorcycleRentingProcessor extends VehicleRentingProcessor {

  private final Motorcycle vehicle;
  private final static int DAILY_RENTAL_COST_LEES_THEN_A_WEEK = 15;
  private final static int DAILY_RENTAL_COST_MORE_THEN_A_WEEK = 10;
  private final static double INSURANCE_COST = 0.02;
  private final static double INSURANCE_INCREASED = 0.2;

  public MotorcycleRentingProcessor(String username, Integer reservedRentalDays, Integer actualRentalDays, Motorcycle vehicle , LocalDate initialDate) throws VehicleException {
    super(username, reservedRentalDays, actualRentalDays, vehicle , initialDate);
    this.vehicle = vehicle;
  }

  @Override
  protected BigDecimal getRentalCostImp() {
    return BigDecimal.valueOf(getRentalCostPerDay(DAILY_RENTAL_COST_LEES_THEN_A_WEEK, DAILY_RENTAL_COST_MORE_THEN_A_WEEK));
  }

  @Override
  protected double getInsuranceCost() {
    return INSURANCE_COST;
  }

  @Override
  protected boolean applyDiscount() {
    // Here can be specified addition login in the feature if needed
    return false;
  }

  @Override
  protected double getDiscountInsurance() {
    // Here can be specified addition login in the feature if needed
    return 0;
  }

  @Override
  protected double getAdditionInsurancesPay() {
    return INSURANCE_INCREASED;
  }

  @Override
  protected boolean applyAdditionPay() {
    return vehicle.getRiderAge() < 25;
  }
}
