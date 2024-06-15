package org.task.processor;

import org.task.domain.enums.RentingDetailsPrinterMessages;
import org.task.domain.processor.PrintingDto;
import org.task.domain.processor.RentingDetailsDto;
import org.task.domain.vehicle.Vehicle;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class RentingDetailsPrinter {

  private final PrintingDto rentalDaysDto;

  public RentingDetailsPrinter(PrintingDto rentalDaysDto) {
    this.rentalDaysDto = rentalDaysDto;
  }

  public void printRentingDetails() {
    RentingDetailsDto detailsDto = rentalDaysDto.getRentingDetailsDto();

    String builder = buildBasicDetails(detailsDto.getVehicle()) +
        RentingDetailsPrinterMessages.RENTAL_COST_PER_DAY.getMessage() + getDecimalFormat().format(detailsDto.getRentalCostPerDay()) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.INITIAL_INSURANCE_COST.getMessage() + getDecimalFormat().format(detailsDto.getInitialInsuranceCostPerDay()) +
        System.lineSeparator() +
        System.lineSeparator() +
        buildEarlyReturnedVehicleDetails(rentalDaysDto.getEarlyReturnedDiscountForRent(), rentalDaysDto.getEarlyReturnedDiscountForInsurance()) +
        buildTotalCostDetails(detailsDto.getRentalCost(), detailsDto.getTotalInsurances(), detailsDto.getTotalCost());

    System.out.println(builder);
  }

  public void printRentingDetailsWithDiscount() {
    DecimalFormat df = getDecimalFormat();
    RentingDetailsDto detailsDto = rentalDaysDto.getRentingDetailsDto();

    String builder = buildBasicDetails(detailsDto.getVehicle()) +
        RentingDetailsPrinterMessages.RENTAL_COST_PER_DAY.getMessage() + df.format(detailsDto.getRentalCostPerDay()) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.INITIAL_INSURANCE_COST.getMessage() + df.format(detailsDto.getInitialInsuranceCostPerDay()) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.INSURANCE_DISCOUNT_PER_DAY.getMessage() + df.format(detailsDto.getInsurancesDiscountPerDay()) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.INSURANCE_PER_DAY.getMessage() + df.format(detailsDto.getInsuranceCostPerDay()) +
        System.lineSeparator() +
        System.lineSeparator() +
        buildEarlyReturnedVehicleDetails(rentalDaysDto.getEarlyReturnedDiscountForRent(), rentalDaysDto.getEarlyReturnedDiscountForInsurance()) +
        buildTotalCostDetails(detailsDto.getRentalCost(), detailsDto.getTotalInsurances(), detailsDto.getTotalCost());

    System.out.println(builder);
  }

  public void printRentingDetailsWithAdditionPay() {
    DecimalFormat df = getDecimalFormat();
    RentingDetailsDto detailsDto = rentalDaysDto.getRentingDetailsDto();

    String builder = buildBasicDetails(detailsDto.getVehicle()) +
        RentingDetailsPrinterMessages.RENTAL_COST_PER_DAY.getMessage() + df.format(detailsDto.getRentalCostPerDay()) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.INITIAL_INSURANCE_COST.getMessage() + df.format(detailsDto.getInitialInsuranceCostPerDay()) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.INSURANCE_ADDITION_PER_DAY.getMessage() + df.format(detailsDto.getAdditionPayInsurancesPerDay()) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.INSURANCE_PER_DAY.getMessage() + df.format(detailsDto.getInsuranceCostPerDay()) +
        System.lineSeparator() +
        System.lineSeparator() +
        buildEarlyReturnedVehicleDetails(rentalDaysDto.getEarlyReturnedDiscountForRent(), rentalDaysDto.getEarlyReturnedDiscountForInsurance()) +
        buildTotalCostDetails(detailsDto.getRentalCost(), detailsDto.getTotalInsurances(), detailsDto.getTotalCost());

    System.out.println(builder);
  }

  private DecimalFormat getDecimalFormat() {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
    symbols.setDecimalSeparator(',');
    DecimalFormat df = new DecimalFormat("#0.00", symbols);
    df.setGroupingUsed(false);
    return df;
  }

  private String buildBasicDetails(Vehicle vehicle) {
    return RentingDetailsPrinterMessages.BORDER.getMessage() +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.DATE.getMessage() + rentalDaysDto.getActualReturnDate() +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.CUSTOM_NAME.getMessage() + rentalDaysDto.getUsername() +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.RENTED_VEHICLE.getMessage() + vehicle.getBrand() + " " + vehicle.getModel() +
        System.lineSeparator() +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.RESERVATION_START_DATE.getMessage() + rentalDaysDto.getReservationStartDate() +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.RESERVATION_END_DATE.getMessage() + rentalDaysDto.getReservationEndDate() +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.RESERVED_RENTAL_DAYS.getMessage() + rentalDaysDto.getReservedRentalDays() + RentingDetailsPrinterMessages.DAYS.getMessage() +
        System.lineSeparator() +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.ACTUAL_RETURN_DATE.getMessage() + rentalDaysDto.getActualReturnDate() +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.ACTUAL_RENTAL_DAYS.getMessage() + rentalDaysDto.getActualRentalDays() + RentingDetailsPrinterMessages.DAYS.getMessage() +
        System.lineSeparator() +
        System.lineSeparator();
  }

  private String buildTotalCostDetails(BigDecimal rentalCost, BigDecimal insuranceCost, BigDecimal totalCost) {
    DecimalFormat df = getDecimalFormat();
    return RentingDetailsPrinterMessages.TOTAL_RENT.getMessage() + df.format(rentalCost) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.TOTAL_INSURANCE_COST.getMessage() + df.format(insuranceCost) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.TOTAL_COST.getMessage() + df.format(totalCost) +
        System.lineSeparator() +
        RentingDetailsPrinterMessages.BORDER.getMessage();
  }

  private String buildEarlyReturnedVehicleDetails(BigDecimal rentDiscount, BigDecimal insuranceDiscount) {
    DecimalFormat df = getDecimalFormat();
    if (rentalDaysDto.getEarlyReturnedDiscountForInsurance() != null) {
      return RentingDetailsPrinterMessages.EARLY_RETURN_DISCOUNT_FOR_RENT.getMessage() + df.format(rentDiscount) +
          System.lineSeparator() +
          RentingDetailsPrinterMessages.EARLY_RETURN_DISCOUNT_FOR_INSURANCES.getMessage() + df.format(insuranceDiscount) +
          System.lineSeparator() +
          System.lineSeparator();
    }
    return "";
  }
}
