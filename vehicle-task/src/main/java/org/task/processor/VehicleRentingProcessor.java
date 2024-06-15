package org.task.processor;

import org.task.domain.processor.PrintingDto;
import org.task.domain.processor.RentingDetailsDto;
import org.task.domain.vehicle.Vehicle;
import org.task.exceptions.VehicleException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public abstract class VehicleRentingProcessor {

  protected final Vehicle vehicle;
  protected final String username;
  protected final LocalDate initialDate;
  protected final int reservedRentalDays;
  protected final int actualRentalDays;
  private final static double EARLY_RETURNED_VEHICLE_RENTAL_COST = 0.5;
  private final static double EARLY_RETURNED_VEHICLE_INSURANCE_COST = 1;

  protected VehicleRentingProcessor(String username, Integer reservedRentalDays, Integer actualRentalDays, Vehicle vehicle, LocalDate initialDate) throws VehicleException {
    if (reservedRentalDays < actualRentalDays) {
      throw new VehicleException("Actual rental days can't be more than reserved rental days");
    }
    if (username == null || username.isEmpty() || username.isBlank()) {
      throw new VehicleException("Username can't be empty");
    }
    this.initialDate = initialDate;
    this.vehicle = vehicle;
    this.username = username;
    this.reservedRentalDays = reservedRentalDays;
    this.actualRentalDays = actualRentalDays;
  }

  protected abstract BigDecimal getRentalCostImp();

  protected abstract double getInsuranceCost();

  protected abstract boolean applyDiscount();

  protected abstract double getDiscountInsurance();

  protected abstract double getAdditionInsurancesPay();

  protected abstract boolean applyAdditionPay();

  public void processRenting() {
    BigDecimal rentCostPerDay = getRentalCostImp();
    BigDecimal totalRentalCost = calculateTotalInsurancesOrTotalRent(rentCostPerDay, reservedRentalDays);
    BigDecimal insurancesPerDay = calculateInsurancePerDay(getInsuranceCost(), vehicle.getValue());

    PrintingDto.Builder printDtoBuilder = new PrintingDto.Builder();

    RentingDetailsDto.Builder builder = new RentingDetailsDto.Builder()
        .vehicle(vehicle)
        .rentalCostPerDay(rentCostPerDay)
        .initialInsuranceCostPerDay(insurancesPerDay)
        .insuranceCostPerDay(insurancesPerDay)
        .rentalCost(totalRentalCost);

    if (applyDiscount()) {

      BigDecimal insureDiscountPerDay = calculateInsurancesWithDiscount(insurancesPerDay, getDiscountInsurance());
      BigDecimal insurePerDayWithDiscount = insurancesPerDay.subtract(insureDiscountPerDay);
      BigDecimal totalInsurances = calculateTotalInsurancesOrTotalRent(insurePerDayWithDiscount, reservedRentalDays);
      BigDecimal totalCost = totalRentalCost.add(totalInsurances);

      builder
          .insurancesDiscountPerDay(insureDiscountPerDay)
          .insuranceCostPerDay(insurePerDayWithDiscount)
          .totalInsurances(totalInsurances)
          .totalCost(totalCost);

    } else if (applyAdditionPay()) {

      BigDecimal insureAdditionalPayPerDay = calculateInsurancesWithAdditionPay(insurancesPerDay, getAdditionInsurancesPay());
      BigDecimal insurePerDayWithAdditionalPay = insurancesPerDay.add(insureAdditionalPayPerDay);
      BigDecimal totalInsurances = calculateTotalInsurancesOrTotalRent(insurePerDayWithAdditionalPay, reservedRentalDays);
      BigDecimal totalCost = totalRentalCost.add(totalInsurances);

      builder
          .totalInsurances(totalInsurances)
          .totalCost(totalCost)
          .additionPayInsurancesPerDay(insureAdditionalPayPerDay)
          .insuranceCostPerDay(insurePerDayWithAdditionalPay);

    } else {

      BigDecimal totalInsurances = calculateTotalInsurancesOrTotalRent(insurancesPerDay, reservedRentalDays);
      BigDecimal totalCost = totalRentalCost.add(totalInsurances);

      builder
          .totalInsurances(totalInsurances)
          .totalCost(totalCost);
    }

    RentingDetailsDto rentingDetailsDto = builder.build();


    if (actualRentalDays < reservedRentalDays) {
      BigDecimal remainingRent = calculateEarlyReturnedVehicleInsuranceCostOrRent(rentingDetailsDto.getRentalCostPerDay(), reservedRentalDays - actualRentalDays, EARLY_RETURNED_VEHICLE_RENTAL_COST);
      BigDecimal remainingIncenses = calculateEarlyReturnedVehicleInsuranceCostOrRent(rentingDetailsDto.getInsuranceCostPerDay(), reservedRentalDays - actualRentalDays, EARLY_RETURNED_VEHICLE_INSURANCE_COST);

      rentingDetailsDto.setTotalInsurances(rentingDetailsDto.getTotalInsurances().subtract(remainingIncenses));
      rentingDetailsDto.setRentalCost(rentingDetailsDto.getRentalCost().subtract(remainingRent));
      rentingDetailsDto.setTotalCost(rentingDetailsDto.getTotalCost().subtract(remainingRent).subtract(remainingIncenses));

      printDtoBuilder
          .earlyReturnedDiscountForInsurance(remainingIncenses)
          .earlyReturnedDiscountForRent(remainingRent);
    }

    LocalDate reservationEndDate = initialDate.plusDays(reservedRentalDays);
    LocalDate actualReturnDate = initialDate.plusDays(actualRentalDays);


    PrintingDto printDto = printDtoBuilder
        .reservedRentalDays(reservedRentalDays)
        .actualRentalDays(actualRentalDays)
        .reservationStartDate(initialDate)
        .reservationEndDate(reservationEndDate)
        .actualReturnDate(actualReturnDate)
        .rentingDetailsDto(rentingDetailsDto)
        .username(username)
        .build();


    RentingDetailsPrinter rentingDetailsPrinter = new RentingDetailsPrinter(printDto);

    if (rentingDetailsDto.getAdditionPayInsurancesPerDay() != null) {
      rentingDetailsPrinter.printRentingDetailsWithAdditionPay();
    } else if (rentingDetailsDto.getInsurancesDiscountPerDay() != null) {
      rentingDetailsPrinter.printRentingDetailsWithDiscount();
    } else {
      rentingDetailsPrinter.printRentingDetails();
    }
  }

  protected int getRentalCostPerDay(int forLessThanWeek, int forMoreThanWeek) {
    return actualRentalDays < 7 ? forLessThanWeek : forMoreThanWeek;
  }

  private BigDecimal calculateInsurancePerDay(double insuranceCost, Double vehicleCost) {
    return BigDecimal.valueOf(insuranceCost)
        .multiply(BigDecimal.valueOf(vehicleCost))
        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
  }

  private BigDecimal calculateTotalInsurancesOrTotalRent(BigDecimal insurancesOrRentPerDay, int days) {
    return insurancesOrRentPerDay.multiply(BigDecimal.valueOf(days))
        .setScale(2, RoundingMode.HALF_UP);
  }

  private BigDecimal calculateInsurancesWithDiscount(BigDecimal insuranceCostPerDay, double discount) {
    return insuranceCostPerDay.multiply(BigDecimal.valueOf(discount))
        .setScale(2, RoundingMode.HALF_UP);
  }

  private BigDecimal calculateInsurancesWithAdditionPay(BigDecimal insuranceCostPerDay, double additionPay) {
    return insuranceCostPerDay.multiply(BigDecimal.valueOf(additionPay))
        .setScale(2, RoundingMode.HALF_UP);
  }

  private BigDecimal calculateEarlyReturnedVehicleInsuranceCostOrRent(BigDecimal insurancesPerDayOrRent, int remainingDays, double earlyReturnedVehicleInsuranceCostOrRent) {
    return insurancesPerDayOrRent.multiply(BigDecimal.valueOf(remainingDays))
        .multiply(BigDecimal.valueOf(earlyReturnedVehicleInsuranceCostOrRent))
        .setScale(2, RoundingMode.HALF_UP);
  }

}
