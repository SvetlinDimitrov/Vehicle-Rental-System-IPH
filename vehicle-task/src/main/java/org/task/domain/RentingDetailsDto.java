package org.task.domain;

import java.math.BigDecimal;

public class RentingDetailsDto {
  private final Vehicle vehicle;
  private final BigDecimal rentalCostPerDay;
  private final BigDecimal initialInsuranceCostPerDay;
  private final BigDecimal insurancesDiscountPerDay;
  private final BigDecimal additionPayInsurancesPerDay;
  private final BigDecimal insuranceCostPerDay;
  private final BigDecimal totalInsurances;
  private final BigDecimal rentalCost;
  private final BigDecimal totalCost;

  private RentingDetailsDto(Builder builder) {
    this.vehicle = builder.vehicle;
    this.rentalCostPerDay = builder.rentalCostPerDay;
    this.initialInsuranceCostPerDay = builder.initialInsuranceCostPerDay;
    this.insurancesDiscountPerDay = builder.insurancesDiscountPerDay;
    this.insuranceCostPerDay = builder.insuranceCostPerDay;
    this.totalInsurances = builder.totalInsurances;
    this.rentalCost = builder.rentalCost;
    this.totalCost = builder.totalCost;
    this.additionPayInsurancesPerDay = builder.additionPayInsurancesPerDay;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public BigDecimal getRentalCostPerDay() {
    return rentalCostPerDay;
  }

  public BigDecimal getInitialInsuranceCostPerDay() {
    return initialInsuranceCostPerDay;
  }

  public BigDecimal getInsurancesDiscountPerDay() {
    return insurancesDiscountPerDay;
  }

  public BigDecimal getInsuranceCostPerDay() {
    return insuranceCostPerDay;
  }

  public BigDecimal getTotalInsurances() {
    return totalInsurances;
  }

  public BigDecimal getRentalCost() {
    return rentalCost;
  }

  public BigDecimal getTotalCost() {
    return totalCost;
  }

  public BigDecimal getAdditionPayInsurancesPerDay() {
    return additionPayInsurancesPerDay;
  }

  public static class Builder {
    private Vehicle vehicle;
    private BigDecimal rentalCostPerDay;
    private BigDecimal initialInsuranceCostPerDay;
    private BigDecimal additionPayInsurancesPerDay;
    private BigDecimal insurancesDiscountPerDay;
    private BigDecimal insuranceCostPerDay;
    private BigDecimal totalInsurances;
    private BigDecimal rentalCost;
    private BigDecimal totalCost;

    public Builder vehicle(Vehicle vehicle) {
      this.vehicle = vehicle;
      return this;
    }

    public Builder rentalCostPerDay(BigDecimal rentalCostPerDay) {
      this.rentalCostPerDay = rentalCostPerDay;
      return this;
    }

    public Builder additionPayInsurancesPerDay(BigDecimal additionPayInsurancesPerDay) {
      this.additionPayInsurancesPerDay = additionPayInsurancesPerDay;
      return this;
    }

    public Builder initialInsuranceCostPerDay(BigDecimal initialInsuranceCostPerDay) {
      this.initialInsuranceCostPerDay = initialInsuranceCostPerDay;
      return this;
    }

    public Builder insurancesDiscountPerDay(BigDecimal insurancesDiscountPerDay) {
      this.insurancesDiscountPerDay = insurancesDiscountPerDay;
      return this;
    }

    public Builder insuranceCostPerDay(BigDecimal insuranceCostPerDay) {
      this.insuranceCostPerDay = insuranceCostPerDay;
      return this;
    }

    public Builder totalInsurances(BigDecimal totalInsurances) {
      this.totalInsurances = totalInsurances;
      return this;
    }

    public Builder rentalCost(BigDecimal rentalCost) {
      this.rentalCost = rentalCost;
      return this;
    }

    public Builder totalCost(BigDecimal totalCost) {
      this.totalCost = totalCost;
      return this;
    }

    public RentingDetailsDto build() {
      return new RentingDetailsDto(this);
    }
  }
}
