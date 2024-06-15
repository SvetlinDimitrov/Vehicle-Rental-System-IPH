package org.task.domain.processor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PrintingDto {

  private String username;
  private int reservedRentalDays;
  private int actualRentalDays;
  private LocalDate reservationStartDate;
  private LocalDate reservationEndDate;
  private LocalDate actualReturnDate;
  private RentingDetailsDto rentingDetailsDto;
  private BigDecimal earlyReturnedDiscountForRent;
  private BigDecimal earlyReturnedDiscountForInsurance;


  private PrintingDto(Builder builder) {
    this.reservedRentalDays = builder.reservedRentalDays;
    this.actualRentalDays = builder.actualRentalDays;
    this.reservationStartDate = builder.reservationStartDate;
    this.reservationEndDate = builder.reservationEndDate;
    this.actualReturnDate = builder.actualReturnDate;
    this.rentingDetailsDto = builder.rentingDetailsDto;
    this.username = builder.username;
    this.earlyReturnedDiscountForRent = builder.earlyReturnedDiscountForRent;
    this.earlyReturnedDiscountForInsurance = builder.earlyReturnedDiscountForInsurance;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setReservedRentalDays(int reservedRentalDays) {
    this.reservedRentalDays = reservedRentalDays;
  }

  public void setActualRentalDays(int actualRentalDays) {
    this.actualRentalDays = actualRentalDays;
  }

  public void setReservationStartDate(LocalDate reservationStartDate) {
    this.reservationStartDate = reservationStartDate;
  }

  public void setReservationEndDate(LocalDate reservationEndDate) {
    this.reservationEndDate = reservationEndDate;
  }

  public void setActualReturnDate(LocalDate actualReturnDate) {
    this.actualReturnDate = actualReturnDate;
  }

  public void setRentingDetailsDto(RentingDetailsDto rentingDetailsDto) {
    this.rentingDetailsDto = rentingDetailsDto;
  }

  public void setEarlyReturnedDiscountForRent(BigDecimal earlyReturnedDiscountForRent) {
    this.earlyReturnedDiscountForRent = earlyReturnedDiscountForRent;
  }

  public void setEarlyReturnedDiscountForInsurance(BigDecimal earlyReturnedDiscountForInsurance) {
    this.earlyReturnedDiscountForInsurance = earlyReturnedDiscountForInsurance;
  }

  public int getReservedRentalDays() {
    return reservedRentalDays;
  }

  public int getActualRentalDays() {
    return actualRentalDays;
  }

  public LocalDate getReservationStartDate() {
    return reservationStartDate;
  }

  public LocalDate getReservationEndDate() {
    return reservationEndDate;
  }

  public LocalDate getActualReturnDate() {
    return actualReturnDate;
  }

  public RentingDetailsDto getRentingDetailsDto() {
    return rentingDetailsDto;
  }

  public String getUsername() {
    return username;
  }

  public BigDecimal getEarlyReturnedDiscountForRent() {
    return earlyReturnedDiscountForRent;
  }

  public BigDecimal getEarlyReturnedDiscountForInsurance() {
    return earlyReturnedDiscountForInsurance;
  }

  public static class Builder {
    private int reservedRentalDays;
    private int actualRentalDays;
    private String username;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private LocalDate actualReturnDate;
    private RentingDetailsDto rentingDetailsDto;
    private BigDecimal earlyReturnedDiscountForRent;
    private BigDecimal earlyReturnedDiscountForInsurance;

    public Builder earlyReturnedDiscountForRent(BigDecimal earlyReturnedDiscountForRent) {
      this.earlyReturnedDiscountForRent = earlyReturnedDiscountForRent;
      return this;
    }

    public Builder earlyReturnedDiscountForInsurance(BigDecimal earlyReturnedDiscountForInsurance) {
      this.earlyReturnedDiscountForInsurance = earlyReturnedDiscountForInsurance;
      return this;
    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder rentingDetailsDto(RentingDetailsDto rentingDetailsDto) {
      this.rentingDetailsDto = rentingDetailsDto;
      return this;
    }

    public Builder reservedRentalDays(int reservedRentalDays) {
      this.reservedRentalDays = reservedRentalDays;
      return this;
    }

    public Builder actualRentalDays(int actualRentalDays) {
      this.actualRentalDays = actualRentalDays;
      return this;
    }

    public Builder reservationStartDate(LocalDate reservationStartDate) {
      this.reservationStartDate = reservationStartDate;
      return this;
    }

    public Builder reservationEndDate(LocalDate reservationEndDate) {
      this.reservationEndDate = reservationEndDate;
      return this;
    }

    public Builder actualReturnDate(LocalDate actualReturnDate) {
      this.actualReturnDate = actualReturnDate;
      return this;
    }

    public PrintingDto build() {
      return new PrintingDto(this);
    }
  }
}
