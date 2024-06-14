package org.task.processor;

import org.task.domain.RentingDetailsDto;
import org.task.domain.Vehicle;
import org.task.exceptions.VehicleException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public abstract class VehicleRentingProcessor {

  protected final int reservedRentalDays;
  protected final int actualRentalDays;
  protected final LocalDate reservationStartDate;
  protected final LocalDate reservationEndDate;
  protected final LocalDate actualReturnDate;

  protected VehicleRentingProcessor(Integer reservedRentalDays, Integer actualRentalDays) throws VehicleException {
    if (reservedRentalDays < actualRentalDays) {
      throw new VehicleException("Actual rental days can't be more than reserved rental days");
    }
    this.reservedRentalDays = reservedRentalDays;
    this.actualRentalDays = actualRentalDays;
    this.reservationStartDate = LocalDate.now();
    this.reservationEndDate = reservationStartDate.plusDays(reservedRentalDays);
    this.actualReturnDate = reservationStartDate.plusDays(actualRentalDays);
  }

  abstract void processRenting();

  protected void printRentingDetails(RentingDetailsDto detailsDto) {

    String basicDetails = buildBasicDetails(detailsDto.getVehicle());
    String totalCostDetails = buildTotalCostDetails(detailsDto.getRentalCost(), detailsDto.getTotalInsurances(), detailsDto.getTotalCost());

    System.out.println(
        basicDetails +
            "Rental cost per day: $" + getDecimalFormat().format(detailsDto.getRentalCost()) + "\n" +
            "Initial insurance cost: $" + getDecimalFormat().format(detailsDto.getInitialInsuranceCostPerDay()) + "\n" +
            totalCostDetails
    );
  }

  protected void printRentingDetailsWithDiscount(RentingDetailsDto detailsDto) {
    DecimalFormat df = getDecimalFormat();

    String basicDetails = buildBasicDetails(detailsDto.getVehicle());
    String totalCostDetails = buildTotalCostDetails(detailsDto.getRentalCost(), detailsDto.getTotalInsurances(), detailsDto.getTotalCost());

    String builder = basicDetails +
        "Rental cost per day: $" + df.format(detailsDto.getRentalCost()) + "\n" +
        "Initial insurance per day: $" + df.format(detailsDto.getInitialInsuranceCostPerDay()) + "\n" +
        "Insurance discount per day: $" + df.format(detailsDto.getInsurancesDiscountPerDay()) + "\n" +
        "Insurance per day: $" + df.format(detailsDto.getInsuranceCostPerDay()) + "\n" +
        totalCostDetails;

    System.out.println(builder);
  }

  protected void printRentingDetailsWithAdditionPay(RentingDetailsDto detailsDto) {
    DecimalFormat df = getDecimalFormat();

    String basicDetails = buildBasicDetails(detailsDto.getVehicle());
    String totalCostDetails = buildTotalCostDetails(detailsDto.getRentalCost(), detailsDto.getTotalInsurances(), detailsDto.getTotalCost());

    String builder = basicDetails +
        "Rental cost per day: $" + df.format(detailsDto.getRentalCostPerDay()) + "\n" +
        "Initial insurance cost: $" + df.format(detailsDto.getInitialInsuranceCostPerDay()) + "\n" +
        "Insurance addition per day: $" + df.format(detailsDto.getAdditionPayInsurancesPerDay()) + "\n" +
        "Insurance per day: $" + df.format(detailsDto.getInsuranceCostPerDay()) + "\n" +
        totalCostDetails;

    System.out.println(builder);
  }

  protected int getRentalCostPerDay(int forLessThanWeek, int forMoreThanWeek) {
    return actualRentalDays < 7 ? forLessThanWeek : forMoreThanWeek;
  }

  protected BigDecimal calculateInsurancePerDay(double insuranceCost, Double vehicleCost) {
    return BigDecimal.valueOf(insuranceCost)
        .multiply(BigDecimal.valueOf(vehicleCost))
        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
  }

  protected BigDecimal calculateTotalInsurances(BigDecimal insurancesPerDay) {
    return insurancesPerDay.multiply(BigDecimal.valueOf(actualRentalDays))
        .setScale(2, RoundingMode.HALF_UP);
  }

  protected BigDecimal calculateInsurancesWithDiscount(BigDecimal insuranceCostPerDay, double discount) {
    return insuranceCostPerDay.multiply(BigDecimal.valueOf(discount))
        .setScale(2, RoundingMode.HALF_UP);
  }

  protected BigDecimal calculateInsurancesWithAdditionPay(BigDecimal insuranceCostPerDay, double additionPay) {
    return insuranceCostPerDay.multiply(BigDecimal.valueOf(additionPay))
        .setScale(2, RoundingMode.HALF_UP);
  }

  protected BigDecimal calculateRentalCost(BigDecimal rentalCost) {

    BigDecimal totalRentalCost = rentalCost.multiply(BigDecimal.valueOf(actualRentalDays))
        .setScale(2, RoundingMode.HALF_UP);

    if (actualRentalDays < reservedRentalDays) {
      int remainingDays = reservedRentalDays - actualRentalDays;
      totalRentalCost = totalRentalCost.add(rentalCost.multiply(BigDecimal.valueOf(remainingDays)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP));
    }

    return totalRentalCost;
  }

  private DecimalFormat getDecimalFormat() {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
    symbols.setDecimalSeparator(',');
    DecimalFormat df = new DecimalFormat("#0.00", symbols);
    df.setGroupingUsed(false);
    return df;
  }

  private String buildBasicDetails(Vehicle vehicle) {
    return
        "XXXXXXXXXX" + "\n" +
            "Date: " + "\n" +
            "Custom Name: " + "\n" +
            "Rented Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel() + "\n" +
            "\n" +
            "Reservation start date: " + reservationStartDate + "\n" +
            "Reservation end date: " + reservationEndDate + "\n" +
            "Reserved rental days: " + reservedRentalDays + " days" + "\n" +
            "\n" +
            "Actual return date: " + actualReturnDate + "\n" +
            "Actual rental days: " + actualRentalDays + " days" + "\n" +
            "\n";
  }

  private String buildTotalCostDetails(BigDecimal rentalCost, BigDecimal insuranceCost, BigDecimal totalCost) {
    DecimalFormat df = getDecimalFormat();
    return "\n" +
        "Total rent: $" + df.format(rentalCost) + "\n" +
        "Total insurance cost: $" + df.format(insuranceCost) + "\n" +
        "Total cost: $" + df.format(totalCost) + "\n" +
        "XXXXXXXXXX";
  }
}
