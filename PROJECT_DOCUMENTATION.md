# Automotive Inventory System - Project Documentation

## Project Overview
The Automotive Inventory System is a comprehensive JavaFX application designed for managing automotive dealership operations. It provides a user-friendly interface for tracking and managing car inventory, employee data, customer information, sales records, and generating reports including commission calculations.

## System Architecture

### Technology Stack
- **Frontend**: JavaFX for the user interface
- **Backend**: Java
- **Database**: Microsoft Access (via UCanAccess JDBC driver)
- **Build Tool**: Maven

### Architecture Components
1. **Presentation Layer**: JavaFX UI components
2. **Business Logic Layer**: Core application logic
3. **Data Access Layer**: DAO pattern implementation
4. **Data Model**: Entity classes representing domain objects
5. **Database**: Microsoft Access database

### Design Patterns
- **Model-View-Controller (MVC)**: Separation of UI, business logic, and data
- **Data Access Object (DAO)**: Abstraction of database operations
- **Singleton**: Database connection management

## Key Features and Functionality

### Car Inventory Management
- Add, update, and delete car records
- Track car details including model, variant, price, mileage, color, type, weight, number plate, and chassis number
- View available cars in stock

### Employee Management
- Maintain employee records with ID, name, salary, and commission rate
- Calculate employee commissions based on sales

### Customer Management
- Store customer information including name, phone number, and email
- Track customer purchase history

### Sales Management
- Record car sales with details of the car, customer, employee, amount, and date
- Generate sales receipts

### Reporting
- Generate sales reports
- Calculate employee commissions
- View inventory status

## Data Model

### Car Entity
- **Attributes**: id, model, variant, price, mileage, color, type, weight, numberPlate, chassisNumber
- **Relationships**: One-to-many with Sale (one car can be in one sale)

### Employee Entity
- **Attributes**: id, name, salary, commissionRate
- **Relationships**: One-to-many with Sale (one employee can be associated with multiple sales)

### Customer Entity
- **Attributes**: id, name, phone, email
- **Relationships**: One-to-many with Sale (one customer can make multiple purchases)

### Sale Entity
- **Attributes**: id, carId, customerId, employeeId, amount, date
- **Relationships**: Many-to-one with Car, Customer, and Employee

## User Interface

### Design Principles
- Dark mode theme for reduced eye strain
- Consistent color scheme with blue as primary color
- Responsive layout
- Intuitive navigation

### UI Components
- Dashboard with menu options
- Forms for data entry and editing
- Tables for data display
- Dialog boxes for confirmations and alerts
- Reports view

### Styling
- Custom CSS for consistent look and feel
- Styled buttons, text fields, and other UI elements
- Visual feedback for user interactions (hover effects, etc.)

## Technical Implementation Details

### Database Access
- UCanAccess JDBC driver for Microsoft Access database
- Connection pooling for efficient database operations
- Prepared statements for secure database queries

### Data Access Objects (DAOs)
- Generic DAO interface defining common CRUD operations
- Specific implementations for each entity type (CarDAO, EmployeeDAO, CustomerDAO, SaleDAO)
- SQL query execution and result mapping

### Sample Data Generation
- DatabasePopulator utility for creating sample data
- Random generation of realistic data for testing and demonstration

### Configuration
- Properties file for configurable settings
- Database path configuration

## Setup and Configuration Instructions

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher
- Microsoft Access (or just the Access Database Engine)

### Installation Steps
1. Clone or download the project repository
2. Navigate to the project directory
3. Build the project using Maven:
   ```
   mvn clean package
   ```
4. Run the application:
   ```
   mvn javafx:run
   ```

### Database Setup
The application uses an Access database to store data. By default, the database will be created at the location specified in the config.properties file.

To populate the database with sample data:
1. Ensure you have Java installed on your system
2. Run the application and the database will be automatically created if it doesn't exist
3. To manually populate the database with sample data, run:
   ```
   mvn exec:java -Dexec.mainClass="com.example.projectfxv5.DatabasePopulator"
   ```

### Configuration Options
The database path can be configured in the `src/main/resources/config.properties` file:
```
db.path=path/to/your/database.accdb
```

## Troubleshooting

### Common Issues
1. **Database Connection Errors**
   - Ensure the database path is correct in config.properties
   - Verify that you have write permissions to the database location
   - Check that UCanAccess dependencies are correctly included

2. **UI Display Issues**
   - Ensure JavaFX is properly configured in your environment
   - Check for CSS loading errors in the console

3. **Build Failures**
   - Verify Maven is correctly installed
   - Check that all dependencies are available

### Logging
The application logs errors to the console, which can help diagnose issues.

## Conclusion
The Automotive Inventory System provides a complete solution for managing automotive dealership operations. Its intuitive interface, comprehensive feature set, and flexible architecture make it suitable for small to medium-sized dealerships looking to digitize their inventory and sales management processes.