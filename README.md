# Automotive Inventory System

This project is a JavaFX application for managing an automotive inventory system. It includes functionality for managing cars, employees, customers, and sales.

## Features

The application provides the following features:
- Car inventory management
- Employee data management
- Customer information tracking
- Sales record keeping
- Sales reports and commission calculations

## Database Setup

The application uses an Access database to store data. The database includes:
- 30 employees
- 40 cars (20 sold, 20 in stock)
- 20 customers
- 20 sales records

### Populating the Database

To populate the database with sample data:

1. Ensure you have Java installed on your system
2. Run the application and the database will be automatically created if it doesn't exist
3. To manually populate the database with sample data, you can run the `DatabasePopulator` class:
   ```
   mvn exec:java -Dexec.mainClass="com.example.projectfxv5.DatabasePopulator"
   ```

### Required Dependencies

The application requires the following dependencies:
- UCanAccess JDBC driver and its dependencies (included in the Maven configuration)
- JavaFX (included in the Maven configuration)

## Database Location

The database will be created in the project directory at:
```
database\AutomotiveInventory.accdb
```

The application will automatically create the `database` directory if it doesn't exist.

If you need to change the database location:
1. Update the `db.path` property in `src/main/resources/config.properties`

## Running the Application

To run the application:

1. Ensure you have Java and Maven installed
2. Build the project:
   ```
   mvn clean package
   ```
3. Run the application:
   ```
   mvn javafx:run
   ```

## Troubleshooting

If you encounter any issues:

1. Make sure Java and Maven are installed and in your PATH
2. Ensure you have write access to the database file
3. Check that the database path is correct in the config.properties file
4. Verify that all required dependencies are correctly specified in the pom.xml file
