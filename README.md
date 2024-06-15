# Project Structure Overview

This project is organized into three main folders: `domain`, `exception`, and `processor`.

## Domain Folder

The `domain` folder contains:
- **enum**: This folder holds the enums which include constant String messages displayed on the CLI.
- **processor**: This folder contains the DTOs for the processor class.
- **vehicle**: This folder contains all the possible vehicle classes.

## Exception Folder

The `exception` folder contains:
- A single exception class that is thrown upon invalid data being entered.

## Processor Folder

The `processor` folder contains all the business logic, similar to a service in a REST architecture.

## Business Logic Explanation

In the `domain` folder, we have a `Vehicle` class which is an abstract class that every new vehicle needs to extend. For each vehicle, there is a corresponding processor implementation, for example, `CarRentingProcessor`, `VanRentingProcessor`, and so on. They all extend the `VehicleRentingProcessor`, which is an abstract class.

The whole business logic follows the Template Method design pattern. The `VehicleRentingProcessor` defines a `processRenting` method which is the main algorithm. The child classes then fill in the gaps of that algorithm depending on their implementation.

### Why Use This Implementation?

This implementation allows you to include a new vehicle and its renting logic easily. All you need to do is:
1. Create the new vehicle entity by extending the `Vehicle` class.
2. Create the corresponding `RentingProcessor` for it by extending the `VehicleRentingProcessor` and implementing its methods.

No modifications to the existing business logic are required. To integrate it with the CLI, simply add the new vehicle name to the vehicle enums and update the `if` statement in the `Main` class.

### Additional Classes

- **RentingDetailsPrinter**: Located in the `processor` folder, this class handles the printing of renting details.
- **CliInputHandler**: This class, created to keep the code cleaner and more readable, handles input from the console in the `Main` class.

## Design Patterns Used

The main design patterns used in this project are:
- Builder
- Template Method

Hope I did not over-engineer anything :)
