package org.task.domain.enums;

public enum RentingDetailsPrinterMessages {
  RENTAL_COST_PER_DAY("Rental cost per day: $"),
  INITIAL_INSURANCE_COST("Initial insurance cost: $"),
  INSURANCE_DISCOUNT_PER_DAY("Insurance discount per day: $"),
  INSURANCE_PER_DAY("Insurance per day: $"),
  INSURANCE_ADDITION_PER_DAY("Insurance addition per day: $"),
  TOTAL_RENT("Total rent: $"),
  TOTAL_INSURANCE_COST("Total insurance cost: $"),
  TOTAL_COST("Total cost: $"),
  EARLY_RETURN_DISCOUNT_FOR_RENT("Early return discount for rent: $"),
  EARLY_RETURN_DISCOUNT_FOR_INSURANCES("Early return discount for insurances: $"),
  BORDER("XXXXXXXXXX"),
  DATE("Date: "),
  CUSTOM_NAME("Custom Name: "),
  RENTED_VEHICLE("Rented Vehicle: "),
  RESERVATION_START_DATE("Reservation start date: "),
  RESERVATION_END_DATE("Reservation end date: "),
  RESERVED_RENTAL_DAYS("Reserved rental days: "),
  ACTUAL_RETURN_DATE("Actual return date: "),
  ACTUAL_RENTAL_DAYS("Actual rental days: "),
  DAYS(" days");

  private final String message;

  RentingDetailsPrinterMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
