# Vehicle-Rental-System

## Note

All of the document test data and more are provided in the test folder for faster and easier checks on the app.

## Project Structure Overview

This project is organized into three main folders: `domain`, `exception`, and `processor`.

### Domain Folder

The `domain` folder contains:
- **enum**: This folder holds the enums which include constant String messages displayed on the CLI.
- **processor**: This folder contains the DTOs for the processor class.
- **vehicle**: This folder contains all the possible vehicle classes.

### Exception Folder

The `exception` folder contains:
- A single exception class that is thrown upon invalid data being entered.

### Processor Folder

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

## How Does It Work?

This is a CLI application, which means when you run the main method, you will need to provide specific information through the console. Here are the steps:

1. **Username**: The application will ask for your username. It cannot be empty.
2. **Vehicle Type**: You will specify the type of vehicle you want to create. The options are Car, Van, or Motorcycle.
3. **Brand, Model, and Price**: You will be prompted to enter the brand and model of the vehicle, both of which cannot be empty. You will also need to enter the price, which should be a positive valid number.
4. **Rental Date**: You will specify the day you want to start the rental. The date should be in the format `yyyy-mm-dd`. If the format is incorrect, you will need to enter it again.
5. **Rental Duration and Actual Rental Days**: You will specify the number of days you want to rent the vehicle and the actual number of days you rented the vehicle. Both should be positive numbers, and the first value (duration) should be greater than the second (actual rental days).
6. **Vehicle-Specific Details**:
   - If you chose a Car, you will need to provide the car's safety rating, which should be between 0 and 5. Otherwise, it will be considered invalid.
   - If you chose a Van, you will need to provide the required driving experience, which should be a valid non-negative number.
   - If you chose a Motorcycle, you will need to provide your age, which should be a valid non-negative number.
   
After entering all the necessary details, you will receive the rental results. If any field is filled incorrectly, an error message will be displayed on the console, and you will need to re-enter the value.

Hope I did not over-engineer anything :)
