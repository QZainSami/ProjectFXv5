# Project Files Explanation

This document provides a simple explanation of each file in the project, describing what they do and why they are included.

## Build System Files

### pom.xml
The Project Object Model (POM) file for Maven. This file defines:
- Project information (name, version)
- Dependencies (external libraries the project needs)
- Build configuration (how to compile and package the application)
- Plugins (tools that help with building and running the application)

This file is essential for Maven to manage the project's build process, dependencies, and packaging.

### mvnw and mvnw.cmd
Maven Wrapper files that allow you to run Maven commands without having Maven installed on your system:
- `mvnw` - Shell script for Unix/Linux/macOS
- `mvnw.cmd` - Batch script for Windows

These files ensure that everyone working on the project uses the same version of Maven.

## Source Code Files

### Main Application Files

#### src\main\java\com\example\projectfxv5\Projectv5.java
The main application class that extends JavaFX's Application class. This file:
- Sets up the user interface
- Handles navigation between different screens
- Contains methods for each major functionality (login, dashboard, stock management, etc.)
- Serves as the entry point for the application

#### src\main\java\com\example\projectfxv5\App.java
An alternative entry point for the application that delegates to Projectv5.

#### src\main\java\com\example\projectfxv5\DatabaseConfig.java
Manages database connections for the application:
- Provides methods to get database connections using the UCanAccess JDBC driver
- Handles connection closing with proper error handling
- Loads database configuration from properties files with fallback to default values
- Centralizes database access logic for the entire application
- Uses a static initialization block to load configuration at class loading time
- Supports Microsoft Access database (.accdb) through the UCanAccess driver
- Implements connection pooling best practices to prevent resource leaks
- Provides clear error messages for connection failures

This class is critical for the application's data access layer as it:
1. Ensures consistent database connectivity throughout the application
2. Abstracts the database connection details from the rest of the code
3. Makes it easy to change database settings without modifying multiple files
4. Handles connection lifecycle (creation and closing) in a standardized way

#### src\main\java\module-info.java
Defines the Java module for this application using the Java Platform Module System (JPMS) introduced in Java 9:
- Declares the module name as `com.example.projectfxv5`
- Specifies required dependencies:
  - `javafx.controls` - Core JavaFX UI components
  - `javafx.fxml` - Support for FXML-based UI definitions
  - `java.sql` - JDBC API for database access
  - `org.controlsfx.controls` - Additional JavaFX controls
  - `com.dlsc.formsfx` - Form building library for JavaFX
  - `org.kordamp.ikonli.javafx` - Icon pack integration for JavaFX
  - `org.kordamp.bootstrapfx.core` - Bootstrap styling for JavaFX
  - `org.apache.pdfbox` - PDF generation and manipulation
- Configures module exports:
  - Opens `com.example.projectfxv5` package to `javafx.fxml` for reflection access
  - Exports `com.example.projectfxv5` package for use by other modules

This file is essential for modern Java applications as it:
1. Enforces strong encapsulation by explicitly declaring which packages are accessible
2. Clearly defines dependencies, making the application more maintainable
3. Enables reliable configuration by ensuring all required modules are available
4. Improves security by limiting reflective access to only specified packages
5. Facilitates better runtime optimization through explicit dependency declarations

### Model Classes

These classes represent the data entities in the application:

#### src\main\java\com\example\projectfxv5\model\Car.java
Represents a car in the inventory with properties like:
- ID, model, variant
- Price, mileage
- Color, type, weight
- Number plate, chassis number

#### src\main\java\com\example\projectfxv5\model\Customer.java
Represents a customer with properties like:
- ID, name
- Contact information
- Address

#### src\main\java\com\example\projectfxv5\model\Employee.java
Represents an employee with properties like:
- ID, name
- Position
- Salary information

#### src\main\java\com\example\projectfxv5\model\Sale.java
Represents a car sale transaction with properties like:
- Sale ID
- Car and customer information
- Employee who made the sale
- Sale date and price

### Data Access Objects (DAO)

These classes handle database operations for each entity:

#### src\main\java\com\example\projectfxv5\dao\DAO.java
A generic interface that defines common CRUD (Create, Read, Update, Delete) operations for all entities.

#### src\main\java\com\example\projectfxv5\dao\CarDAO.java
Implements the DAO interface for Car entities:
- Retrieves cars from the database
- Adds new cars to inventory
- Updates existing car information
- Deletes cars from inventory

#### src\main\java\com\example\projectfxv5\dao\CustomerDAO.java
Implements the DAO interface for Customer entities:
- Manages customer records in the database
- Provides methods to search and retrieve customer information

#### src\main\java\com\example\projectfxv5\dao\EmployeeDAO.java
Implements the DAO interface for Employee entities:
- Manages employee records
- Provides methods for employee data operations

#### src\main\java\com\example\projectfxv5\dao\SaleDAO.java
Implements the DAO interface for Sale entities:
- Records new sales
- Retrieves sales history
- Generates sales reports

## Resource Files

### CSS Files

#### src\main\resources\com\example\projectfxv5\css\styles.css
Contains styling information for the JavaFX user interface:
- Defines colors, fonts, and layouts
- Customizes the appearance of UI components
- Makes the application visually appealing and consistent

### Image Files

Located in `src\main\resources\com\example\projectfxv5\images\`:

#### Main_NoBG4.png and main_logo.png
Logo images used in the application's interface.

#### Various icon files (commission_icon.png, customer_icon.png, etc.)
Icons used for buttons and navigation elements in the dashboard:
- stock_icon.png - For inventory management
- customer_icon.png - For customer management
- employee_icon.png - For employee management
- sales_icon.png - For sales operations
- report_icon.png - For reporting features
- commission_icon.png - For commission tracking

## Configuration Files

#### src\main\resources\config.properties
Contains configuration settings for the application:
- Database connection information
- Application settings
- Paths to resources

## Database Files

#### database\AutomotiveInventory.accdb
Microsoft Access database file that stores all the application data:
- Car inventory
- Customer information
- Employee records
- Sales transactions

## External Libraries

Located in the `lib` directory:

#### commons-lang3-3.8.1.jar
Apache Commons Lang library providing utilities for Java language features.

#### commons-logging-1.2.jar
Apache Commons Logging library for logging capabilities.

#### hsqldb-2.5.0.jar
HyperSQL Database engine, possibly used for testing or as an alternative database.

#### jackcess-3.0.1.jar
Library for reading from and writing to Microsoft Access databases.

#### ucanaccess-5.0.1.jar
JDBC driver that allows Java to connect to Microsoft Access databases.

## Documentation Files

#### README.md
Provides an overview of the project, setup instructions, and basic usage information.

#### PROJECT_DOCUMENTATION.md
Contains detailed documentation about the project's architecture, features, and implementation details.

#### PROJECT_OVERVIEW.md
Gives a high-level overview of the project's purpose, goals, and main components.

## Compiled Files

The `target` directory contains compiled class files and resources generated during the build process. These files are created automatically by Maven and should not be edited directly.
