package org.task.processor;

import org.task.domain.vehicle.Van;
import org.task.exceptions.VehicleException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VanRentingProcessor extends VehicleRentingProcessor{

  private final Van vehicle;
  private final static int DAILY_RENTAL_COST_LEES_THEN_A_WEEK = 50;
  private final static int DAILY_RENTAL_COST_MORE_THEN_A_WEEK = 40;
  private final static double INSURANCE_COST = 0.03;
  private final static double INSURANCE_DISCOUNT = 0.15;

  public VanRentingProcessor(String username, int reservedRentalDays, int actualRentalDays, Van vehicle , LocalDate initialDate) throws VehicleException {
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
    return vehicle.getDriverExperience() > 5;
  }

  @Override
  protected double getDiscountInsurance() {
    return INSURANCE_DISCOUNT;
  }

  @Override
  protected double getAdditionInsurancesPay() {
    // Here can be specified addition login for increasing the insurance in the feature
    return 0;
  }

  @Override
  protected boolean applyAdditionPay() {
    // Here can be specified addition login for increasing the insurance in the feature
    return false;
  }
}
