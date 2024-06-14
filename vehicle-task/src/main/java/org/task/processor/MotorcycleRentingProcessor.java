package org.task.processor;

import org.task.domain.Motorcycle;
import org.task.domain.RentingDetailsDto;
import org.task.exceptions.VehicleException;

import java.math.BigDecimal;

public class MotorcycleRentingProcessor extends VehicleRentingProcessor {

  private final Motorcycle vehicle;
  private final static int DAILY_RENTAL_COST_LEES_THEN_A_WEEK = 15;
  private final static int DAILY_RENTAL_COST_MORE_THEN_A_WEEK = 10;
  private final static double INSURANCE_COST = 0.02;
  private final static double INSURANCE_INCREASED = 0.2;

  public MotorcycleRentingProcessor(Integer reservedRentalDays, Integer actualRentalDays, Motorcycle vehicle) throws VehicleException {
    super(reservedRentalDays, actualRentalDays);
    this.vehicle = vehicle;
  }

  @Override
  public void processRenting() {
    BigDecimal realRentalCost = BigDecimal.valueOf(getRentalCostPerDay(DAILY_RENTAL_COST_LEES_THEN_A_WEEK, DAILY_RENTAL_COST_MORE_THEN_A_WEEK));

    BigDecimal rentalCost = calculateRentalCost(realRentalCost);

    BigDecimal insurePerDay = calculateInsurancePerDay(INSURANCE_COST, vehicle.getValue());

    if (vehicle.getRiderAge() < 25) {

      BigDecimal insureAdditionalPayPerDay = calculateInsurancesWithAdditionPay(insurePerDay, INSURANCE_INCREASED);
      BigDecimal insurePerDayWithAdditionalPay = insurePerDay.add(insureAdditionalPayPerDay);
      BigDecimal totalInsurances = calculateTotalInsurances(insurePerDayWithAdditionalPay);
      BigDecimal totalCost = rentalCost.add(totalInsurances);

      RentingDetailsDto rentingDetailsDto = new RentingDetailsDto.Builder()
          .vehicle(vehicle)
          .rentalCostPerDay(realRentalCost)
          .initialInsuranceCostPerDay(insurePerDay)
          .additionPayInsurancesPerDay(insureAdditionalPayPerDay)
          .insuranceCostPerDay(insurePerDayWithAdditionalPay)
          .totalInsurances(totalInsurances)
          .rentalCost(rentalCost)
          .totalCost(totalCost)
          .build();

      printRentingDetailsWithAdditionPay(rentingDetailsDto);

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
