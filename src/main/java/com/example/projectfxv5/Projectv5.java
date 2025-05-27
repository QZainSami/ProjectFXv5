package com.example.projectfxv5;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.projectfxv5.dao.CarDAO;
import com.example.projectfxv5.dao.CustomerDAO;
import com.example.projectfxv5.dao.EmployeeDAO;
import com.example.projectfxv5.dao.SaleDAO;
import com.example.projectfxv5.dao.UserDAO;
import com.example.projectfxv5.model.Car;
import com.example.projectfxv5.model.Customer;
import com.example.projectfxv5.model.Employee;
import com.example.projectfxv5.model.Sale;
import com.example.projectfxv5.model.User;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main application class for the Automotive Inventory System. This JavaFX
 * application provides a user interface for managing: - Car inventory -
 * Employee data - Customer information - Sales records - Reports and commission
 * calculations
 */
public class Projectv5 extends Application {

    // DAO instances
    private final CarDAO carDAO = new CarDAO();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final SaleDAO saleDAO = new SaleDAO();
    private final UserDAO userDAO = new UserDAO();

    /**
     * Helper method to load images from resources. Handles resource loading and
     * error reporting.
     *
     * @param path The path to the image resource
     * @return The loaded Image object or null if loading fails
     */
    private Image loadImage(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is != null) {
                return new Image(is);
            } else {
                System.err.println("Could not load image: " + path);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JavaFX application entry point. Initializes and displays the login
     * window.
     *
     * @param primaryStage The primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        showLoginWindow(primaryStage);
    }

    /**
     * Displays the login window. This is the first screen users see before
     * accessing the main dashboard.
     *
     * @param primaryStage The primary stage for this application
     */
    private void showLoginWindow(Stage primaryStage) {
        // Load CSS
        Scene scene = new Scene(new VBox(), 450, 600);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Add a subtle glow effect to the container
        mainLayout.setStyle("-fx-effect: dropshadow(gaussian, rgba(52, 152, 219, 0.4), 15, 0, 0, 0);");

        // Header with logo
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.getStyleClass().add("logo-container");

        // Load main logo
        Image logoImage = loadImage("/com/example/projectfxv5/images/Main_NoBG4.png");
        if (logoImage != null) {
            ImageView logoView = new ImageView(logoImage);
            logoView.setFitWidth(150);
            logoView.setPreserveRatio(true);

            // Add a subtle glow effect to the logo
            logoView.setEffect(new javafx.scene.effect.Glow(0.3));

            header.getChildren().add(logoView);
        }

        // Title and subtitle
        Label title = new Label("Platinum Auto Japan");
        title.getStyleClass().add("dashboard-title");

        Label subtitle = new Label("Please login to continue");
        subtitle.getStyleClass().add("dashboard-subtitle");

        header.getChildren().addAll(title, subtitle);
        mainLayout.setTop(header);

        // Login form
        VBox loginForm = new VBox(15);
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPadding(new Insets(20));
        loginForm.getStyleClass().add("form-container");

        // Add a subtle border glow to the form
        loginForm.setStyle("-fx-border-color: rgba(52, 152, 219, 0.5); -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Username field with icon
        HBox usernameBox = new HBox(10);
        usernameBox.setAlignment(Pos.CENTER_LEFT);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setPrefWidth(250);

        // Add focus effect to text field
        usernameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                usernameField.setStyle("-fx-border-color: #3498db; -fx-border-width: 2px;");
            } else {
                usernameField.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px;");
            }
        });

        usernameBox.getChildren().addAll(usernameLabel);

        // Password field with icon
        HBox passwordBox = new HBox(10);
        passwordBox.setAlignment(Pos.CENTER_LEFT);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setPrefWidth(250);

        // Add focus effect to password field
        passwordField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                passwordField.setStyle("-fx-border-color: #3498db; -fx-border-width: 2px;");
            } else {
                passwordField.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px;");
            }
        });

        passwordBox.getChildren().addAll(passwordLabel);

        // Login button with hover effect
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("success-button");
        loginButton.setPrefWidth(250);

        // Add a subtle shadow effect to the button
        loginButton.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);");

        // Error message label
        Label errorLabel = new Label("");
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        errorLabel.setVisible(false);

        // Create a separator for visual appeal
        Separator separator = new Separator();
        separator.setPrefWidth(250);
        separator.setOpacity(0.3);

        // Add login form elements
        loginForm.getChildren().addAll(
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                separator,
                errorLabel,
                loginButton
        );

        // Set login button action with animation
        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Username and password are required");
                errorLabel.setVisible(true);
                // ...shake animation...
                return;
            }

            try {
                User user = userDAO.getUserByUsername(username);
                if (user == null || !user.getPassword().equals(password)) {
                    errorLabel.setText("Invalid username or password");
                    errorLabel.setVisible(true);
                    // ...shake animation...
                } else {
                    // Success animation - fade out login form and show dashboard
                    javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(javafx.util.Duration.millis(600), mainLayout);
                    fadeOut.setFromValue(1.0);
                    fadeOut.setToValue(0.0);
                    fadeOut.setOnFinished(event -> showMainDashboard(primaryStage));
                    fadeOut.play();
                }
            } catch (Exception ex) {
                errorLabel.setText("Database error: " + ex.getMessage());
                errorLabel.setVisible(true);
            }
        });

        // Add keyboard event handler for Enter key
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                loginButton.fire();
            }
        });

        // Add form to center of layout
        mainLayout.setCenter(loginForm);

        // Footer
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(10));
        Label footerLabel = new Label("© 2025 Platinum Auto Japan");
        footerLabel.getStyleClass().add("dashboard-subtitle");
        footer.getChildren().add(footerLabel);
        mainLayout.setBottom(footer);

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login - Platinum Auto Japan");

        // Add a fade-in animation when the login window appears
        javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(javafx.util.Duration.millis(800), mainLayout);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        primaryStage.show();
    }

    /**
     * Displays the main dashboard with menu options. This is the primary
     * navigation screen of the application.
     *
     * @param primaryStage The primary stage for this application
     */
    private void showMainDashboard(Stage primaryStage) {
        // Load CSS
        Scene scene = new Scene(new VBox(), 850, 1000);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Header with logo
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.getStyleClass().add("logo-container");

        // Load main logo
        Image logoImage = loadImage("/com/example/projectfxv5/images/Main_NoBG4.png");
        if (logoImage != null) {
            ImageView logoView = new ImageView(logoImage);
            logoView.setFitWidth(200);
            logoView.setPreserveRatio(true);
            header.getChildren().add(logoView);
        }

        // Title and subtitle
        Label title = new Label("Platinum Auto Japan");
        title.getStyleClass().add("dashboard-title");

        Label subtitle = new Label("Japanese automotive excellence since 2017");
        subtitle.getStyleClass().add("dashboard-subtitle");

        header.getChildren().addAll(title, subtitle);
        mainLayout.setTop(header);

        // Menu buttons in a grid
        GridPane menuGrid = new GridPane();
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setHgap(20);
        menuGrid.setVgap(20);
        menuGrid.setPadding(new Insets(30));

        // Create buttons with icons
        Button viewStock = createDashboardButton("View Stock", "/com/example/projectfxv5/images/stock_icon.png");
        Button employeeData = createDashboardButton("Employee Data", "/com/example/projectfxv5/images/employee_icon.png");
        Button customerData = createDashboardButton("Customer Data", "/com/example/projectfxv5/images/customer_icon.png");
        Button sales = createDashboardButton("Sales", "/com/example/projectfxv5/images/sales_icon.png");
        Button report = createDashboardButton("Report", "/com/example/projectfxv5/images/report_icon.png");
        Button commission = createDashboardButton("Commission", "/com/example/projectfxv5/images/commission_icon.png");

        // Set button actions
        viewStock.setOnAction(e -> showStockWindow(primaryStage));
        employeeData.setOnAction(e -> showEmployeeDataWindow(primaryStage));
        customerData.setOnAction(e -> showCustomerDataWindow(primaryStage));
        sales.setOnAction(e -> showSalesWindow(primaryStage));
        report.setOnAction(e -> showReportWindow(primaryStage));
        commission.setOnAction(e -> showCommissionWindow(primaryStage));

        // Add buttons to grid (2 columns, 3 rows)
        menuGrid.add(viewStock, 0, 0);
        menuGrid.add(employeeData, 1, 0);
        menuGrid.add(customerData, 0, 1);
        menuGrid.add(sales, 1, 1);
        menuGrid.add(report, 0, 2);
        menuGrid.add(commission, 1, 2);

        mainLayout.setCenter(menuGrid);

        // Footer
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(10));
        Label footerLabel = new Label("© 2025 Platinum Auto Japan");
        footerLabel.getStyleClass().add("dashboard-subtitle");
        footer.getChildren().add(footerLabel);
        mainLayout.setBottom(footer);

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard - Platinum Auto Japan");
        primaryStage.show();
    }

    /**
     * Creates a styled dashboard button with an icon and text.
     *
     * @param text The text to display on the button
     * @param iconPath The path to the icon image resource
     * @return A styled Button with icon and text
     */
    private Button createDashboardButton(String text, String iconPath) {
        // Create button without text to avoid duplication
        Button button = new Button();
        button.setPrefSize(200, 120);

        // Create a VBox to hold the icon and text vertically
        VBox content = new VBox(10);
        content.setAlignment(Pos.CENTER);

        // Load icon
        Image icon = loadImage(iconPath);
        if (icon != null) {
            ImageView iconView = new ImageView(icon);
            // Increase icon size for better visibility
            iconView.setFitHeight(64);
            iconView.setFitWidth(64);

            // Add effect to make icon more prominent
            StackPane iconContainer = new StackPane();
            iconContainer.getChildren().add(iconView);
            iconContainer.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 50%;");
            iconContainer.setPadding(new Insets(8));

            content.getChildren().add(iconContainer);
        }

        // Add text label
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white;"); // Ensure text is visible on dark background
        content.getChildren().add(label);

        // Set the VBox as the graphic for the button
        button.setGraphic(content);
        button.setContentDisplay(ContentDisplay.TOP);

        return button;
    }

    /**
     * Displays the car stock management window. Allows adding, deleting, and
     * viewing all cars in inventory.
     *
     * @param primaryStage The primary stage for this application
     */
    private void showStockWindow(Stage primaryStage) {
        // Load CSS if not already loaded
        Scene scene = new Scene(new VBox(), 800, 900);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Header with title and back button
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));

        Button backBtn = new Button("← Back");
        backBtn.getStyleClass().add("back-button");
        backBtn.setOnAction(e -> showMainDashboard(primaryStage));

        // Add icon to header
        Image stockIcon = loadImage("/com/example/projectfxv5/images/stock_icon.png");
        if (stockIcon != null) {
            ImageView iconView = new ImageView(stockIcon);
            iconView.setFitHeight(40);
            iconView.setFitWidth(40);
            header.getChildren().add(iconView);
        }

        Label title = new Label("Car Stock Management");
        title.getStyleClass().add("section-header");

        header.getChildren().addAll(backBtn, title);
        mainLayout.setTop(header);

        // Action buttons
        HBox actionButtons = new HBox(15);
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setPadding(new Insets(15));

        Button addCar = new Button("Add Car");
        addCar.getStyleClass().add("action-button");

        Button deleteCar = new Button("Delete Car");
        deleteCar.getStyleClass().add("action-button");

        // Button to record a vehicle sale
        Button sellCar = new Button("Sell Car");
        sellCar.getStyleClass().add("action-button");

        Button showAllCars = new Button("Show All Cars");
        showAllCars.getStyleClass().add("success-button");

        actionButtons.getChildren().addAll(addCar, deleteCar, sellCar, showAllCars);

        // Text area for displaying cars
        TextArea stockArea = new TextArea();
        stockArea.setEditable(false);
        stockArea.setPrefHeight(700);
        stockArea.setPrefWidth(600);
        stockArea.setFont(javafx.scene.text.Font.font("Monospaced", 12));
        stockArea.setWrapText(false);

        // Main content
        VBox content = new VBox(15);
        content.setPadding(new Insets(15));
        content.getChildren().addAll(actionButtons, stockArea);
        mainLayout.setCenter(content);

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Stock Management - Platinum Auto Japan");

        // Sell Car button action
        sellCar.setOnAction(e -> {
            Stage sellStage = new Stage();
            GridPane form = new GridPane();
            form.setPadding(new Insets(10));
            form.setVgap(10);
            form.setHgap(10);

            // Create form fields
            TextField saleIdField = new TextField();
            saleIdField.setEditable(false); // Make the field non-editable as it will be auto-generated

            // Auto-generate the sale ID
            try {
                String nextSaleId = saleDAO.generateNextSaleId();
                saleIdField.setText(nextSaleId);
            } catch (SQLException ex) {
                System.err.println("Error generating sale ID: " + ex.getMessage());
                ex.printStackTrace();
            }

            ComboBox<String> carComboBox = new ComboBox<>();
            ComboBox<String> customerComboBox = new ComboBox<>();
            ComboBox<String> employeeComboBox = new ComboBox<>();
            TextField amountField = new TextField();

            // Get lists of cars, customers, and employees for validation and dropdowns
            try {
                List<Car> availableCars = carDAO.getAllCars();
                for (Car car : availableCars) {
                    carComboBox.getItems().add(car.getId() + " - " + car.getModel() + " " + car.getVariant());
                }

                List<Customer> customers = customerDAO.getAllCustomers();
                for (Customer customer : customers) {
                    customerComboBox.getItems().add(customer.getId() + " - " + customer.getName());
                }

                List<Employee> employees = employeeDAO.getAllEmployees();
                for (Employee employee : employees) {
                    employeeComboBox.getItems().add(employee.getId() + " - " + employee.getName());
                }
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText("Error Loading Data");
                alert.setContentText("Could not load data from database: " + ex.getMessage());
                alert.showAndWait();
            }

            // Add form fields to the grid
            form.addRow(0, new Label("Sale ID:"), saleIdField);
            form.addRow(1, new Label("Car:"), carComboBox);
            form.addRow(2, new Label("Customer:"), customerComboBox);
            form.addRow(3, new Label("Employee:"), employeeComboBox);
            form.addRow(4, new Label("Sale Amount:"), amountField);

            Button saveBtn = new Button("Complete Sale");

            saveBtn.setOnAction(ev -> {
                try {
                    // Validate form fields
                    if (carComboBox.getValue() == null
                            || customerComboBox.getValue() == null
                            || employeeComboBox.getValue() == null
                            || amountField.getText().isEmpty()) {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Validation Error");
                        alert.setHeaderText("Missing Information");
                        alert.setContentText("Please fill in all fields to complete the sale.");
                        alert.showAndWait();
                        return;
                    }

                    // Extract IDs from the selected values
                    String carId = carComboBox.getValue().split(" - ")[0];
                    String customerId = customerComboBox.getValue().split(" - ")[0];
                    String employeeId = employeeComboBox.getValue().split(" - ")[0];

                    // Get current date
                    LocalDate currentDate = LocalDate.now();
                    String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

                    // Create a new Sale object from form data
                    Sale sale = new Sale(
                            saleIdField.getText(),
                            carId,
                            customerId,
                            employeeId,
                            Double.parseDouble(amountField.getText()),
                            formattedDate
                    );

                    // Save the sale to the database
                    try {
                        saleDAO.addSale(sale);
                    } catch (SQLException ex) {
                        throw new RuntimeException("Database error while saving sale: " + ex.getMessage(), ex);
                    }

                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Sale Completed");
                    alert.setContentText("Vehicle " + carComboBox.getValue()
                            + " has been sold to " + customerComboBox.getValue()
                            + " by " + employeeComboBox.getValue());
                    alert.showAndWait();

                    sellStage.close();

                    // Refresh the car list to reflect the changes
                    showAllCars.fire();

                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Sale Error");
                    alert.setContentText("Error completing sale: " + ex.getMessage());
                    alert.showAndWait();
                }
            });

            HBox buttonBox = new HBox(10);
            buttonBox.setAlignment(Pos.CENTER_RIGHT);
            buttonBox.getChildren().add(saveBtn);

            VBox vbox = new VBox(15);
            vbox.setPadding(new Insets(10));
            vbox.getChildren().addAll(
                    new Label("Enter Sale Details"),
                    form,
                    buttonBox
            );

            Scene formScene = new Scene(vbox);
            formScene.getStylesheets().add(css);

            sellStage.setScene(formScene);
            sellStage.setTitle("Sell Vehicle");
            sellStage.show();
        });

        addCar.setOnAction(e -> {
            Stage addStage = new Stage();
            GridPane form = new GridPane();
            form.setPadding(new Insets(10));
            form.setVgap(10);
            form.setHgap(10);

            TextField idField = new TextField();
            TextField modelField = new TextField();
            TextField variantField = new TextField();
            TextField priceField = new TextField();
            TextField mileageField = new TextField();
            TextField colorField = new TextField();
            TextField typeField = new TextField();
            TextField weightField = new TextField();
            TextField numberPlateField = new TextField();
            TextField chassisField = new TextField();

            form.addRow(0, new Label("ID:"), idField);
            form.addRow(1, new Label("Model:"), modelField);
            form.addRow(2, new Label("Variant:"), variantField);
            form.addRow(3, new Label("Price:"), priceField);
            form.addRow(4, new Label("Mileage:"), mileageField);
            form.addRow(5, new Label("Color:"), colorField);
            form.addRow(6, new Label("Type:"), typeField);
            form.addRow(7, new Label("Weight (kg):"), weightField);
            form.addRow(8, new Label("Number Plate:"), numberPlateField);
            form.addRow(9, new Label("Chassis No."), chassisField);

            Button saveBtn = new Button("Save");

            saveBtn.setOnAction(ev -> {
                try {
                    // Create a new Car object from form data
                    Car car = new Car(
                            idField.getText(),
                            modelField.getText(),
                            variantField.getText(),
                            Double.parseDouble(priceField.getText()),
                            Double.parseDouble(mileageField.getText()),
                            colorField.getText(),
                            typeField.getText(),
                            Double.parseDouble(weightField.getText()),
                            numberPlateField.getText(),
                            chassisField.getText()
                    );

                    // Use the DAO to add the car to the database
                    carDAO.add(car);
                    addStage.close();
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,
                            "Please enter valid numeric values for Price, Mileage, and Weight.");
                    alert.showAndWait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving car: " + ex.getMessage());
                    alert.showAndWait();
                }
            });

            // Apply CSS to form
            form.getStyleClass().add("form-container");
            saveBtn.getStyleClass().add("success-button");

            // Add title to form
            Label formTitle = new Label("Add New Car");
            formTitle.getStyleClass().add("form-title");

            VBox formLayout = new VBox(15, formTitle, form, saveBtn);
            formLayout.getStyleClass().add("form-container");
            formLayout.setPadding(new Insets(20));
            formLayout.setAlignment(Pos.CENTER);

            // Create scene with CSS
            Scene formScene = new Scene(formLayout, 500, 600);
            String formCss = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
            formScene.getStylesheets().add(formCss);

            addStage.setScene(formScene);
            addStage.setTitle("Add Car");
            addStage.show();
        });

        deleteCar.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Car ID to Delete:");
            dialog.showAndWait().ifPresent(carId -> {
                try {
                    // Use the DAO to delete the car
                    int affected = carDAO.delete(carId);
                    if (affected == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "No car found with ID: " + carId);
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Car deleted successfully.");
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting car: " + ex.getMessage());
                    alert.showAndWait();
                }
            });
        });

        showAllCars.setOnAction(e -> {
            // Create a new stage for the table view
            Stage tableStage = new Stage();
            tableStage.setTitle("All Cars - Table View");

            TableView<Car> carTable = new TableView<>();
            carTable.setPrefHeight(700);
            carTable.setPrefWidth(900);

            // Define columns
            TableColumn<Car, String> idCol = new TableColumn<>("ID");
            idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));

            TableColumn<Car, String> modelCol = new TableColumn<>("Model");
            modelCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getModel()));

            TableColumn<Car, String> variantCol = new TableColumn<>("Variant");
            variantCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getVariant()));

            TableColumn<Car, Double> priceCol = new TableColumn<>("Price");
            priceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());

            TableColumn<Car, Double> mileageCol = new TableColumn<>("Mileage");
            mileageCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getMileage()).asObject());

            TableColumn<Car, String> colorCol = new TableColumn<>("Color");
            colorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getColor()));

            TableColumn<Car, String> typeCol = new TableColumn<>("Type");
            typeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));

            TableColumn<Car, Double> weightCol = new TableColumn<>("Weight");
            weightCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getWeight()).asObject());

            TableColumn<Car, String> numberPlateCol = new TableColumn<>("Number Plate");
            numberPlateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumberPlate()));

            TableColumn<Car, String> chassisCol = new TableColumn<>("Chassis No.");
            chassisCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getChassisNumber()));

            carTable.getColumns().addAll(idCol, modelCol, variantCol, priceCol, mileageCol, colorCol, typeCol, weightCol, numberPlateCol, chassisCol);

            // Load data
            try {
                List<Car> cars = carDAO.getAll();
                carTable.getItems().setAll(cars);
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading cars: " + ex.getMessage());
                alert.showAndWait();
                return;
            }

            VBox vbox = new VBox(carTable);
            vbox.setPadding(new Insets(15));
            Scene tableScene = new Scene(vbox, 1000, 700);
            String tableCss = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
            tableScene.getStylesheets().add(tableCss);

            tableStage.setScene(tableScene);
            tableStage.show();
        });
    }

    /**
     * Displays the employee data window. Shows a list of all employees with
     * their details and provides a form to add new employees.
     *
     * @param primaryStage The primary stage for this application
     */
    private void showEmployeeDataWindow(Stage primaryStage) {
        // Load CSS
        Scene scene = new Scene(new VBox(), 800, 900);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Header with title and back button
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));

        Button backBtn = new Button("← Back");
        backBtn.getStyleClass().add("back-button");
        backBtn.setOnAction(e -> showMainDashboard(primaryStage));

        // Add icon to header
        Image employeeIcon = loadImage("/com/example/projectfxv5/images/employee_icon.png");
        if (employeeIcon != null) {
            ImageView iconView = new ImageView(employeeIcon);
            iconView.setFitHeight(40);
            iconView.setFitWidth(40);
            header.getChildren().add(iconView);
        }

        Label title = new Label("Employee Management");
        title.getStyleClass().add("section-header");

        header.getChildren().addAll(backBtn, title);
        mainLayout.setTop(header);

        // Main content
        VBox content = new VBox(20);
        content.setPadding(new Insets(15));

        // Form for adding new employees
        Label addEmployeeLabel = new Label("Add New Employee:");
        addEmployeeLabel.getStyleClass().add("section-header");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        // Form fields
        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label salaryLabel = new Label("Salary:");
        TextField salaryField = new TextField();

        Button addButton = new Button("Add Employee");
        addButton.getStyleClass().add("action-button");

        // Add form elements to grid
        formGrid.add(idLabel, 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.add(nameLabel, 0, 1);
        formGrid.add(nameField, 1, 1);
        formGrid.add(salaryLabel, 0, 2);
        formGrid.add(salaryField, 1, 2);
        formGrid.add(addButton, 1, 3);

        // Status label for feedback
        Label statusLabel = new Label("");
        statusLabel.getStyleClass().add("status-label");

        // Text area for displaying employees
        Label employeeListLabel = new Label("Employee Details:");
        employeeListLabel.getStyleClass().add("section-header");

        TextArea area = new TextArea();
        area.setEditable(false);
        area.setPrefHeight(300);

        // Add button action
        addButton.setOnAction(e -> {
            try {
                // Validate input
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String salaryText = salaryField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || salaryText.isEmpty()) {
                    statusLabel.setText("All fields are required!");
                    statusLabel.getStyleClass().add("error-text");
                    return;
                }

                double salary;

                try {
                    salary = Double.parseDouble(salaryText);
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Salary must be a valid number!");
                    statusLabel.getStyleClass().add("error-text");
                    return;
                }

                // Create and add employee
                Employee newEmployee = new Employee(id, name, salary);
                employeeDAO.add(newEmployee);

                // Clear form and update status
                idField.clear();
                nameField.clear();
                salaryField.clear();

                statusLabel.setText("Employee added successfully!");
                statusLabel.getStyleClass().remove("error-text");

                // Refresh employee list
                refreshEmployeeList(area);

            } catch (Exception ex) {
                ex.printStackTrace();
                statusLabel.setText("Error adding employee: " + ex.getMessage());
                statusLabel.getStyleClass().add("error-text");
            }
        });

        // Add all components to content
        content.getChildren().addAll(
                addEmployeeLabel, formGrid, statusLabel,
                new Separator(), employeeListLabel, area
        );

        mainLayout.setCenter(content);

        // Initial load of employee list
        refreshEmployeeList(area);

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Employee Data - Platinum Auto Japan");
    }

    /**
     * Helper method to refresh the employee list in the text area.
     *
     * @param area The TextArea to update with employee data
     */
    private void refreshEmployeeList(TextArea area) {
        try {
            // Clear existing content
            area.clear();

            // Use the DAO to get all employees
            List<Employee> employees = employeeDAO.getAll();

            if (employees.isEmpty()) {
                area.setText("No employees found in the database.");
            } else {
                // Display each employee in the text area
                for (Employee employee : employees) {
                    area.appendText("- Employee:\n"
                            + "ID: " + employee.getId() + "\n"
                            + "Name: " + employee.getName() + "\n"
                            + "Salary: " + employee.getSalary() + "\n\n"
                            + "----------------------------------------\n\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            area.setText("Failed to load employee data: " + e.getMessage());
        }
    }

    /**
     * Displays the customer data window. Shows a list of all customers with
     * their details and provides a form to add new customers.
     *
     * @param primaryStage The primary stage for this application
     */
    private void showCustomerDataWindow(Stage primaryStage) {
        // Load CSS
        Scene scene = new Scene(new VBox(), 800, 900);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Header with title and back button
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));

        Button backBtn = new Button("← Back");
        backBtn.getStyleClass().add("back-button");
        backBtn.setOnAction(e -> showMainDashboard(primaryStage));

        // Add icon to header
        Image customerIcon = loadImage("/com/example/projectfxv5/images/customer_icon.png");
        if (customerIcon != null) {
            ImageView iconView = new ImageView(customerIcon);
            iconView.setFitHeight(40);
            iconView.setFitWidth(40);
            header.getChildren().add(iconView);
        }

        Label title = new Label("Customer Management");
        title.getStyleClass().add("section-header");

        header.getChildren().addAll(backBtn, title);
        mainLayout.setTop(header);

        // Main content
        VBox content = new VBox(20);
        content.setPadding(new Insets(15));

        // Form for adding new customers
        Label addCustomerLabel = new Label("Add New Customer:");
        addCustomerLabel.getStyleClass().add("section-header");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        // Form fields
        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Button addButton = new Button("Add Customer");
        addButton.getStyleClass().add("action-button");

        // Add form elements to grid
        formGrid.add(idLabel, 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.add(nameLabel, 0, 1);
        formGrid.add(nameField, 1, 1);
        formGrid.add(phoneLabel, 0, 2);
        formGrid.add(phoneField, 1, 2);
        formGrid.add(emailLabel, 0, 3);
        formGrid.add(emailField, 1, 3);
        formGrid.add(addButton, 1, 4);

        // Status label for feedback
        Label statusLabel = new Label("");
        statusLabel.getStyleClass().add("status-label");

        // Text area for displaying customers
        Label customerListLabel = new Label("Customer Details:");
        customerListLabel.getStyleClass().add("section-header");

        TextArea area = new TextArea();
        area.setEditable(false);
        area.setPrefHeight(300);

        // Add button action
        addButton.setOnAction(e -> {
            try {
                // Validate input
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    statusLabel.setText("All fields are required!");
                    statusLabel.getStyleClass().add("error-text");
                    return;
                }

                // Create and add customer
                Customer newCustomer = new Customer(id, name, phone, email);
                customerDAO.add(newCustomer);

                // Clear form and update status
                idField.clear();
                nameField.clear();
                phoneField.clear();
                emailField.clear();

                statusLabel.setText("Customer added successfully!");
                statusLabel.getStyleClass().remove("error-text");

                // Refresh customer list
                refreshCustomerList(area);

            } catch (Exception ex) {
                ex.printStackTrace();
                statusLabel.setText("Error adding customer: " + ex.getMessage());
                statusLabel.getStyleClass().add("error-text");
            }
        });

        // Add all components to content
        content.getChildren().addAll(
                addCustomerLabel, formGrid, statusLabel,
                new Separator(), customerListLabel, area
        );

        mainLayout.setCenter(content);

        // Initial load of customer list
        refreshCustomerList(area);

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer Data - Platinum Auto Japan");
    }

    /**
     * Helper method to refresh the customer list in the text area.
     *
     * @param area The TextArea to update with customer data
     */
    private void refreshCustomerList(TextArea area) {
        try {
            // Clear existing content
            area.clear();

            // Use the DAO to get all customers
            List<Customer> customers = customerDAO.getAll();

            if (customers.isEmpty()) {
                area.setText("No customers found in the database.");
            } else {
                // Display each customer in the text area
                for (Customer customer : customers) {
                    area.appendText("- Customer:\n"
                            + "ID: " + customer.getId() + "\n"
                            + "Name: " + customer.getName() + "\n"
                            + "Phone: " + customer.getPhone() + "\n"
                            + "Email: " + customer.getEmail() + "\n\n"
                            + "----------------------------------------\n\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            area.setText("Failed to load customer data: " + e.getMessage());
        }
    }

    /**
     * Displays the sales management window. Allows creating new sales and
     * managing existing ones.
     *
     * @param primaryStage The primary stage for this application
     */
    private void showSalesWindow(Stage primaryStage) {
        // Load CSS
        Scene scene = new Scene(new VBox(), 800, 900);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Header with title and back button
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));

        Button backBtn = new Button("← Back");
        backBtn.getStyleClass().add("back-button");
        backBtn.setOnAction(e -> showMainDashboard(primaryStage));

        // Add icon to header
        Image salesIcon = loadImage("/com/example/projectfxv5/images/sales_icon.png");
        if (salesIcon != null) {
            ImageView iconView = new ImageView(salesIcon);
            iconView.setFitHeight(40);
            iconView.setFitWidth(40);
            header.getChildren().add(iconView);
        }

        Label title = new Label("Sales Management");
        title.getStyleClass().add("section-header");

        header.getChildren().addAll(backBtn, title);
        mainLayout.setTop(header);

        // Main content
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);

        // Create new sale section
        VBox newSaleSection = new VBox(15);
        newSaleSection.getStyleClass().add("section-container");
        newSaleSection.setPadding(new Insets(20));

        Label newSaleLabel = new Label("Create New Sale");
        newSaleLabel.getStyleClass().add("section-header");
        newSaleSection.getChildren().add(newSaleLabel);

        // Form for creating a new sale
        GridPane saleForm = new GridPane();
        saleForm.setHgap(15);
        saleForm.setVgap(15);
        saleForm.setPadding(new Insets(10, 0, 10, 0));

        // Car selection
        Label carLabel = new Label("Select Car:");
        ComboBox<String> carComboBox = new ComboBox<>();
        carComboBox.setPrefWidth(300);

        // Customer selection
        Label customerLabel = new Label("Select Customer:");
        ComboBox<String> customerComboBox = new ComboBox<>();
        customerComboBox.setPrefWidth(300);

        // Employee selection
        Label employeeLabel = new Label("Select Employee:");
        ComboBox<String> employeeComboBox = new ComboBox<>();
        employeeComboBox.setPrefWidth(300);

        // Sale amount
        Label amountLabel = new Label("Sale Amount:");
        TextField amountField = new TextField();
        amountField.setPrefWidth(300);

        // Sale date
        Label dateLabel = new Label("Sale Date:");
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setPrefWidth(300);

        // Add form elements to grid
        saleForm.add(carLabel, 0, 0);
        saleForm.add(carComboBox, 1, 0);
        saleForm.add(customerLabel, 0, 1);
        saleForm.add(customerComboBox, 1, 1);
        saleForm.add(employeeLabel, 0, 2);
        saleForm.add(employeeComboBox, 1, 2);
        saleForm.add(amountLabel, 0, 3);
        saleForm.add(amountField, 1, 3);
        saleForm.add(dateLabel, 0, 4);
        saleForm.add(datePicker, 1, 4);

        newSaleSection.getChildren().add(saleForm);

        // Create sale button
        Button createSaleBtn = new Button("Create Sale");
        createSaleBtn.getStyleClass().add("action-button");
        createSaleBtn.setPrefWidth(200);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(createSaleBtn);

        newSaleSection.getChildren().add(buttonBox);

        // Recent sales section
        VBox recentSalesSection = new VBox(15);
        recentSalesSection.getStyleClass().add("section-container");
        recentSalesSection.setPadding(new Insets(20));

        Label recentSalesLabel = new Label("Recent Sales");
        recentSalesLabel.getStyleClass().add("section-header");
        recentSalesSection.getChildren().add(recentSalesLabel);

        // Table for recent sales
        TableView<Sale> salesTable = new TableView<>();
        salesTable.setPrefHeight(300);

        // Define columns
        TableColumn<Sale, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));

        TableColumn<Sale, String> carColumn = new TableColumn<>("Car");
        carColumn.setCellValueFactory(data -> {
            try {
                Car car = carDAO.getById(data.getValue().getCarId());
                return new javafx.beans.property.SimpleStringProperty(
                        car != null ? car.getModel() + " " + car.getVariant() : data.getValue().getCarId());
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty(data.getValue().getCarId());
            }
        });

        TableColumn<Sale, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(data -> {
            try {
                Customer customer = customerDAO.getById(data.getValue().getCustomerId());
                return new javafx.beans.property.SimpleStringProperty(
                        customer != null ? customer.getName() : data.getValue().getCustomerId());
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomerId());
            }
        });

        TableColumn<Sale, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(data
                -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getAmount()).asObject());

        TableColumn<Sale, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDate()));

        // Add columns to table
        salesTable.getColumns().addAll(idColumn, carColumn, customerColumn, amountColumn, dateColumn);

        recentSalesSection.getChildren().add(salesTable);

        // Add sections to content
        content.getChildren().addAll(newSaleSection, recentSalesSection);

        // Add scrolling for the entire content
        ScrollPane contentScrollPane = new ScrollPane(content);
        contentScrollPane.setFitToWidth(true);
        contentScrollPane.setFitToHeight(true);
        mainLayout.setCenter(contentScrollPane);

        try {
            // Populate car combo box
            List<Car> availableCars = carDAO.getAll();
            for (Car car : availableCars) {
                // Only show cars that haven't been sold yet
                boolean isSold = false;
                try {
                    List<Sale> sales = saleDAO.getAll();
                    for (Sale sale : sales) {
                        if (sale.getCarId().equals(car.getId())) {
                            isSold = true;
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!isSold) {
                    carComboBox.getItems().add(car.getId() + " - " + car.getModel() + " " + car.getVariant());
                }
            }

            // Populate customer combo box
            List<Customer> customers = customerDAO.getAll();
            for (Customer customer : customers) {
                customerComboBox.getItems().add(customer.getId() + " - " + customer.getName());
            }

            // Populate employee combo box
            List<Employee> employees = employeeDAO.getAll();
            for (Employee employee : employees) {
                employeeComboBox.getItems().add(employee.getId() + " - " + employee.getName());
            }

            // Populate recent sales table
            List<Sale> recentSales = saleDAO.getAll();
            salesTable.getItems().addAll(recentSales);

            // Create sale button action
            createSaleBtn.setOnAction(e -> {
                try {
                    // Validate inputs
                    if (carComboBox.getValue() == null || customerComboBox.getValue() == null
                            || employeeComboBox.getValue() == null || amountField.getText().isEmpty()
                            || datePicker.getValue() == null) {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Input Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill in all fields to create a sale.");
                        alert.showAndWait();
                        return;
                    }

                    // Extract IDs from combo box selections
                    String carId = carComboBox.getValue().split(" - ")[0];
                    String customerId = customerComboBox.getValue().split(" - ")[0];
                    String employeeId = employeeComboBox.getValue().split(" - ")[0];

                    // Parse amount
                    double amount;
                    try {
                        amount = Double.parseDouble(amountField.getText());
                    } catch (NumberFormatException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Input Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a valid amount.");
                        alert.showAndWait();
                        return;
                    }

                    // Format date
                    String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    // Generate sale ID
                    String saleId = "SALE" + String.format("%03d", saleDAO.getAll().size() + 1);

                    // Create and save the sale
                    Sale newSale = new Sale(saleId, carId, customerId, employeeId, amount, date);
                    saleDAO.add(newSale);

                    // Update the table
                    salesTable.getItems().add(newSale);

                    // Remove the sold car from the combo box
                    carComboBox.getItems().removeIf(item -> item.startsWith(carId + " - "));

                    // Clear form
                    carComboBox.setValue(null);
                    customerComboBox.setValue(null);
                    employeeComboBox.setValue(null);
                    amountField.clear();
                    datePicker.setValue(LocalDate.now());

                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Sale created successfully!");
                    alert.showAndWait();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to create sale: " + ex.getMessage());
                    alert.showAndWait();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load data: " + e.getMessage());
            alert.showAndWait();
        }

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sales Management - Platinum Auto Japan");
    }

    /**
     * Saves the sales report as a text file.
     *
     * @param content The content to save
     */
    private void saveSalesReportAsTxt(String content) {
        try {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Save Sales Report");
            fileChooser.getExtensionFilters().add(
                    new javafx.stage.FileChooser.ExtensionFilter("Text Files", "*.txt"));
            fileChooser.setInitialFileName("sales_report.txt");

            java.io.File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                java.io.FileWriter fileWriter = new java.io.FileWriter(file);
                fileWriter.write(content);
                fileWriter.close();

                // Show success message
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Sales report saved successfully as TXT file!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();

            // Show error message
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to save sales report: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Saves the sales report as a PDF file.
     *
     * @param content The content to save
     */
    private void saveSalesReportAsPdf(String content) {
        try {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Save Sales Report");
            fileChooser.getExtensionFilters().add(
                    new javafx.stage.FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName("sales_report.pdf");

            java.io.File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                // Create a new PDF document
                org.apache.pdfbox.pdmodel.PDDocument document = new org.apache.pdfbox.pdmodel.PDDocument();
                org.apache.pdfbox.pdmodel.PDPage page = new org.apache.pdfbox.pdmodel.PDPage();
                document.addPage(page);

                // Create a content stream for adding content to the page
                org.apache.pdfbox.pdmodel.PDPageContentStream contentStream
                        = new org.apache.pdfbox.pdmodel.PDPageContentStream(document, page);

                // Set font and font size
                contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER, 12);

                // Start text
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                // Add title
                contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER_BOLD, 16);
                contentStream.showText("Sales Report");
                contentStream.newLineAtOffset(0, -20);

                // Add content line by line
                contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER, 10);
                float leading = 12;

                String[] lines = content.split("\n");
                for (String line : lines) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -leading);
                }

                // End text
                contentStream.endText();
                contentStream.close();

                // Save the document
                document.save(file);
                document.close();

                // Show success message
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Sales report saved successfully as PDF file!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();

            // Show error message
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to save sales report: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Displays the sales report window. Shows a summary of all sales with
     * vehicle, customer, employee, and price information.
     *
     * @param primaryStage The primary stage for this application
     */
    private void showReportWindow(Stage primaryStage) {
        // Load CSS
        Scene scene = new Scene(new VBox(), 800, 900);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Header with title and back button
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));

        Button backBtn = new Button("← Back");
        backBtn.getStyleClass().add("back-button");
        backBtn.setOnAction(e -> showMainDashboard(primaryStage));

        // Add icon to header
        Image reportIcon = loadImage("/com/example/projectfxv5/images/report_icon.png");
        if (reportIcon != null) {
            ImageView iconView = new ImageView(reportIcon);
            iconView.setFitHeight(40);
            iconView.setFitWidth(40);
            header.getChildren().add(iconView);
        }

        Label title = new Label("Sales Report");
        title.getStyleClass().add("section-header");

        header.getChildren().addAll(backBtn, title);
        mainLayout.setTop(header);

        // TableView for displaying sales
        TableView<Sale> salesTable = new TableView<>();
        salesTable.setPrefHeight(700);

        // Define columns
        TableColumn<Sale, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));

        TableColumn<Sale, String> carColumn = new TableColumn<>("Car");
        carColumn.setCellValueFactory(data -> {
            try {
                Car car = carDAO.getById(data.getValue().getCarId());
                return new javafx.beans.property.SimpleStringProperty(
                        car != null ? car.getModel() + " " + car.getVariant() : data.getValue().getCarId());
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty(data.getValue().getCarId());
            }
        });

        TableColumn<Sale, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(data -> {
            try {
                Customer customer = customerDAO.getById(data.getValue().getCustomerId());
                return new javafx.beans.property.SimpleStringProperty(
                        customer != null ? customer.getName() : data.getValue().getCustomerId());
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomerId());
            }
        });

        TableColumn<Sale, String> employeeColumn = new TableColumn<>("Employee");
        employeeColumn.setCellValueFactory(data -> {
            try {
                Employee employee = employeeDAO.getById(data.getValue().getEmployeeId());
                return new javafx.beans.property.SimpleStringProperty(
                        employee != null ? employee.getName() : data.getValue().getEmployeeId());
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty(data.getValue().getEmployeeId());
            }
        });

        TableColumn<Sale, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(data
                -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getAmount()).asObject());

        TableColumn<Sale, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDate()));

        salesTable.getColumns().addAll(idColumn, carColumn, customerColumn, employeeColumn, amountColumn, dateColumn);

        // Populate table
        try {
            List<Sale> sales = saleDAO.getAll();
            salesTable.getItems().addAll(sales);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Download report button
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        MenuButton downloadBtn = new MenuButton("Download Report");
        downloadBtn.getStyleClass().add("action-button");
        MenuItem downloadTxtItem = new MenuItem("Download as TXT");
        MenuItem downloadPdfItem = new MenuItem("Download as PDF");
        downloadBtn.getItems().addAll(downloadTxtItem, downloadPdfItem);
        buttonBox.getChildren().add(downloadBtn);

        // Download actions
        downloadTxtItem.setOnAction(e -> {
            StringBuilder report = new StringBuilder();
            // Header
            report.append(String.format("%-8s %-25s %-20s %-20s %-10s %-12s\n",
                    "ID", "Car", "Customer", "Employee", "Amount", "Date"));
            report.append("---------------------------------------------------------------------------------------------\n");
            // Rows
            for (Sale sale : salesTable.getItems()) {
                String carName = sale.getCarId();
                String customerName = sale.getCustomerId();
                String employeeName = sale.getEmployeeId();
                try {
                    Car car = carDAO.getById(sale.getCarId());
                    if (car != null) {
                        carName = car.getModel() + " " + car.getVariant();
                    }
                } catch (Exception ignored) {
                }
                try {
                    Customer customer = customerDAO.getById(sale.getCustomerId());
                    if (customer != null) {
                        customerName = customer.getName();
                    }
                } catch (Exception ignored) {
                }
                try {
                    Employee employee = employeeDAO.getById(sale.getEmployeeId());
                    if (employee != null) {
                        employeeName = employee.getName();
                    }
                } catch (Exception ignored) {
                }
                report.append(String.format("%-8s %-25s %-20s %-20s %-10.2f %-12s\n",
                        sale.getId(), carName, customerName, employeeName, sale.getAmount(), sale.getDate()));
            }
            saveSalesReportAsTxt(report.toString());
        });

        downloadPdfItem.setOnAction(e -> {
            StringBuilder report = new StringBuilder();
            // Header
            report.append(String.format("%-8s %-15s %-12s %-12s %-10s %-12s\n",
                    "ID", "Car", "Customer", "Employee", "Amount", "Date"));
            report.append("------------------------------------------------------------------------\n");
            for (Sale sale : salesTable.getItems()) {
                String carName = sale.getCarId();
                String customerName = sale.getCustomerId();
                String employeeName = sale.getEmployeeId();
                try {
                    Car car = carDAO.getById(sale.getCarId());
                    if (car != null) {
                        carName = (car.getModel() + " " + car.getVariant());
                    }
                } catch (Exception ignored) {
                }
                try {
                    Customer customer = customerDAO.getById(sale.getCustomerId());
                    if (customer != null) {
                        customerName = customer.getName();
                    }
                } catch (Exception ignored) {
                }
                try {
                    Employee employee = employeeDAO.getById(sale.getEmployeeId());
                    if (employee != null) {
                        employeeName = employee.getName();
                    }
                } catch (Exception ignored) {
                }
                // Truncate values if too long
                if (carName.length() > 14) {
                    carName = carName.substring(0, 14);
                }
                if (customerName.length() > 11) {
                    customerName = customerName.substring(0, 11);
                }
                if (employeeName.length() > 11) {
                    employeeName = employeeName.substring(0, 11);
                }
                report.append(String.format("%-8s %-15s %-12s %-12s %-10.2f %-12s\n",
                        sale.getId(), carName, customerName, employeeName, sale.getAmount(), sale.getDate()));
            }
            saveSalesReportAsPdf(report.toString());
        });

        VBox content = new VBox(15);
        content.setPadding(new Insets(15));
        Label sectionLabel = new Label("Sales Summary Report:");
        sectionLabel.getStyleClass().add("section-header");
        content.getChildren().addAll(sectionLabel, buttonBox, salesTable);
        mainLayout.setCenter(content);

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sales Report - Platinum Auto Japan");
    }

    /**
     * Displays the commission viewer window. Shows a list of employees that can
     * be selected to view their commission details.
     *
     * @param primaryStage The primary stage for this application
     */
    private void showCommissionWindow(Stage primaryStage) {
        // Load CSS
        Scene scene = new Scene(new VBox(), 800, 900);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Header with title and back button
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));

        Button backBtn = new Button("← Back");
        backBtn.getStyleClass().add("back-button");
        backBtn.setOnAction(e -> showMainDashboard(primaryStage));

        // Add icon to header
        Image commissionIcon = loadImage("/com/example/projectfxv5/images/commission_icon.png");
        if (commissionIcon != null) {
            ImageView iconView = new ImageView(commissionIcon);
            iconView.setFitHeight(40);
            iconView.setFitWidth(40);
            header.getChildren().add(iconView);
        }

        Label title = new Label("Commission Viewer");
        title.getStyleClass().add("section-header");

        header.getChildren().addAll(backBtn, title);
        mainLayout.setTop(header);

        // Main content
        VBox content = new VBox(15);
        content.setPadding(new Insets(15));
        content.setAlignment(Pos.CENTER);

        Label sectionLabel = new Label("Select an Employee to View Commission:");
        sectionLabel.getStyleClass().add("section-header");
        content.getChildren().add(sectionLabel);

        // Grid for employee buttons
        GridPane employeeGrid = new GridPane();
        employeeGrid.setStyle("-fx-background-color: #212235;");
        employeeGrid.setAlignment(Pos.CENTER);
        employeeGrid.setHgap(15);
        employeeGrid.setVgap(15);
        employeeGrid.setPadding(new Insets(20));

        try {
            // Use the DAO to get all employees
            List<Employee> employees = employeeDAO.getAll();

            if (employees.isEmpty()) {
                Label noEmployeesLabel = new Label("No employees found in the database.");
                noEmployeesLabel.getStyleClass().add("dashboard-subtitle");
                content.getChildren().add(noEmployeesLabel);
            } else {
                // Create a button for each employee
                int row = 0;
                int col = 0;
                int maxCols = 2; // 2 columns of buttons

                for (Employee employee : employees) {
                    String empId = employee.getId();
                    String empName = employee.getName();

                    // Create button without text to avoid duplication
                    Button empBtn = new Button();
                    empBtn.getStyleClass().add("action-button");
                    empBtn.setPrefSize(200, 80);

                    // Create a VBox for button content
                    VBox buttonContent = new VBox(5);
                    buttonContent.setAlignment(Pos.CENTER);

                    Label nameLabel = new Label(empName);
                    nameLabel.setStyle("-fx-font-weight: bold;");
                    Label idLabel = new Label("ID: " + empId);

                    buttonContent.getChildren().addAll(nameLabel, idLabel);
                    empBtn.setGraphic(buttonContent);
                    empBtn.setContentDisplay(ContentDisplay.CENTER);

                    empBtn.setOnAction(e -> showCommissionDetails(primaryStage, empId, empName));

                    employeeGrid.add(empBtn, col, row);

                    // Move to next column or row
                    col++;
                    if (col >= maxCols) {
                        col = 0;
                        row++;
                    }
                }

                // Wrap the grid in a ScrollPane to allow scrolling through all employees
                ScrollPane scrollPane = new ScrollPane(employeeGrid);
                scrollPane.setFitToWidth(true);
                scrollPane.setPrefHeight(700);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                content.getChildren().add(scrollPane);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Label errorLabel = new Label("Failed to load employee list: " + e.getMessage());
            errorLabel.getStyleClass().add("dashboard-subtitle");
            content.getChildren().add(errorLabel);
        }

        mainLayout.setCenter(content);

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Commission Viewer - Platinum Auto Japan");
    }

    /**
     * Displays detailed commission information for a specific employee. Shows a
     * breakdown of sales and calculated commissions.
     *
     * @param primaryStage The primary stage for this application
     * @param empId The ID of the employee
     * @param empName The name of the employee
     */
    private void showCommissionDetails(Stage primaryStage, String empId, String empName) {
        // Load CSS
        Scene scene = new Scene(new VBox(), 800, 900);
        String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Main container
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("dashboard-container");

        // Header with title and back button
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));

        Button backBtn = new Button("← Back");
        backBtn.getStyleClass().add("back-button");
        backBtn.setOnAction(e -> showCommissionWindow(primaryStage));

        // Add icon to header
        Image commissionIcon = loadImage("/com/example/projectfxv5/images/commission_icon.png");
        if (commissionIcon != null) {
            ImageView iconView = new ImageView(commissionIcon);
            iconView.setFitHeight(40);
            iconView.setFitWidth(40);
            header.getChildren().add(iconView);
        }

        Label title = new Label("Commission Details");
        title.getStyleClass().add("section-header");

        header.getChildren().addAll(backBtn, title);
        mainLayout.setTop(header);

        // Text area for displaying commission details
        TextArea area = new TextArea();
        area.setEditable(false);
        area.setPrefHeight(400);
        area.setStyle("-fx-font-family: 'Courier New', monospace; -fx-font-size: 12px;");

        // Wrap the text area in a ScrollPane to ensure all content is accessible
        ScrollPane scrollPane = new ScrollPane(area);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Main content
        VBox content = new VBox(15);
        content.setPadding(new Insets(15));

        // Employee info section
        HBox employeeInfo = new HBox(10);
        employeeInfo.setAlignment(Pos.CENTER_LEFT);

        Label employeeLabel = new Label("Commission Details for: ");
        employeeLabel.getStyleClass().add("section-header");

        Label employeeName = new Label(empName);
        employeeName.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #3498db;");

        employeeInfo.getChildren().addAll(employeeLabel, employeeName);

        content.getChildren().addAll(employeeInfo, scrollPane);
        mainLayout.setCenter(content);

        StringBuilder report = new StringBuilder();
        report.append(String.format("%-30s %-15s %-15s\n", "Car", "Price", "Commission"));
        report.append("------------------------------------------------------------\n");

        double totalCommission = 0;
        double totalSales = 0;

        try {
            // Use the SaleDAO to get sales for this employee
            List<Sale> employeeSales = saleDAO.getSalesByEmployeeId(empId);

            if (employeeSales.isEmpty()) {
                area.setText("No sales found for this employee.");
            } else {
                // Get the employee to check their commission rate
                double commissionRate = 0.10;

                // Add commission rate info
                report.append(String.format("Commission Rate: %.1f%%\n\n", commissionRate * 100));

                // Process each sale
                for (Sale sale : employeeSales) {
                    // Get the car details
                    Car car = carDAO.getById(sale.getCarId());

                    if (car != null) {
                        String carName = car.getModel() + " " + car.getVariant();
                        double price = sale.getAmount();
                        totalSales += price;

                        double commission = price * commissionRate;
                        totalCommission += commission;

                        report.append(String.format("%-30s Rs.%-13.2f Rs.%-13.2f\n", carName, price, commission));
                    }
                }

                report.append("\n------------------------------------------------------------\n");
                report.append(String.format("Total Sales:      Rs.%.2f\n", totalSales));
                report.append(String.format("Total Commission: Rs.%.2f", totalCommission));
                area.setText(report.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            area.setText("Failed to load commission details: " + e.getMessage());
        }

        // Set the scene
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Commission Details - " + empName + " - Platinum Auto Japan");
    }

    /**
     * Main entry point for the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
