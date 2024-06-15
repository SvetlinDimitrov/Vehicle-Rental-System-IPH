package org.task.processor;

import org.task.domain.vehicle.Car;
import org.task.exceptions.VehicleException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CarRentingProcessor extends VehicleRentingProcessor {

  private final Car vehicle;
  private final static int DAILY_RENTAL_COST_LEES_THEN_A_WEEK = 20;
  private final static int DAILY_RENTAL_COST_MORE_THEN_A_WEEK = 15;
  private final static double INSURANCE_COST = 0.01;
  private final static double INSURANCE_DISCOUNT = 0.1;

  public CarRentingProcessor(String username, int reservedRentalDays, int actualRentalDays, Car vehicle, LocalDate initialDate) throws VehicleException {
    super(username, reservedRentalDays, actualRentalDays, vehicle, initialDate);
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
    return vehicle.getIsHighSafetyRating();
  }

  @Override
  protected double getDiscountInsurance() {
    return INSURANCE_DISCOUNT;
  }

  @Override
  protected double getAdditionInsurancesPay() {
    // Here can be specified addition login in the feature if needed
    return 0;
  }

  @Override
  protected boolean applyAdditionPay() {
    // Here can be specified addition login in the feature if needed
    return false;
  }

}
