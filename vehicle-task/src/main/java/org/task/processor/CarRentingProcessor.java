package org.task.processor;

import org.task.domain.Car;
import org.task.domain.RentingDetailsDto;
import org.task.exceptions.VehicleException;

import java.math.BigDecimal;

public class CarRentingProcessor extends VehicleRentingProcessor {

  private final Car vehicle;
  private final static int DAILY_RENTAL_COST_LEES_THEN_A_WEEK = 20;
  private final static int DAILY_RENTAL_COST_MORE_THEN_A_WEEK = 15;
  private final static double INSURANCE_COST = 0.01;
  private final static double INSURANCE_DISCOUNT = 0.1;

  public CarRentingProcessor(Car vehicle, int reservedRentalDays, int actualRentalDays) throws VehicleException {
    super(reservedRentalDays, actualRentalDays);
    this.vehicle = vehicle;
  }

  @Override
  public void processRenting() {
    BigDecimal realRentalCost = BigDecimal.valueOf(getRentalCostPerDay(DAILY_RENTAL_COST_LEES_THEN_A_WEEK, DAILY_RENTAL_COST_MORE_THEN_A_WEEK));

    BigDecimal rentalCost = calculateRentalCost(realRentalCost);

    BigDecimal insurePerDay = calculateInsurancePerDay(INSURANCE_COST, vehicle.getValue());

    if (vehicle.getIsHighSafetyRating()) {

      BigDecimal insureDiscountPerDay = calculateInsurancesWithDiscount(insurePerDay, INSURANCE_DISCOUNT);
      BigDecimal insurePerDayWithDiscount = insurePerDay.subtract(insureDiscountPerDay);
      BigDecimal totalInsurances = calculateTotalInsurances(insurePerDayWithDiscount);
      BigDecimal totalCost = rentalCost.add(totalInsurances);

      RentingDetailsDto rentingDetailsDto = new RentingDetailsDto.Builder()
          .vehicle(vehicle)
          .rentalCostPerDay(realRentalCost)
          .initialInsuranceCostPerDay(insurePerDay)
          .insurancesDiscountPerDay(insureDiscountPerDay)
          .insuranceCostPerDay(insurePerDayWithDiscount)
          .totalInsurances(totalInsurances)
          .rentalCost(rentalCost)
          .totalCost(totalCost)
          .build();

      printRentingDetailsWithDiscount(rentingDetailsDto);

    } else {
      BigDecimal totalInsurances = calculateTotalInsurances(insurePerDay);
      BigDecimal totalCost = rentalCost.add(totalInsurances);

      RentingDetailsDto rentingDetailsDto = new RentingDetailsDto.Builder()
          .vehicle(vehicle)
          .rentalCostPerDay(realRentalCost)
          .initialInsuranceCostPerDay(insurePerDay)
          .totalInsurances(totalInsurances)
          .rentalCost(rentalCost)
          .totalCost(totalCost)
          .build();

      printRentingDetails(rentingDetailsDto);
    }
  }


}
