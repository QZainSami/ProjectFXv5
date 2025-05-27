# Line-by-Line Explanation of Projectv5.java

This document provides a simple explanation of every line of code in the `Projectv5.java` file, which is the main application class for the Automotive Inventory System.

## Package Declaration and Imports (Lines 1-26)

Line 1: `package com.example.projectfxv5;`
- Declares that this file belongs to the "com.example.projectfxv5" package, which helps organize the code.

Lines 3-6:
```
import com.example.projectfxv5.dao.CarDAO;
import com.example.projectfxv5.dao.CustomerDAO;
import com.example.projectfxv5.dao.EmployeeDAO;
import com.example.projectfxv5.dao.SaleDAO;
```
- Imports the Data Access Object (DAO) classes that handle database operations for cars, customers, employees, and sales.

Lines 7-10:
```
import com.example.projectfxv5.model.Car;
import com.example.projectfxv5.model.Customer;
import com.example.projectfxv5.model.Employee;
import com.example.projectfxv5.model.Sale;
```
- Imports the model classes that represent the data entities (cars, customers, employees, and sales).

Lines 12-20:
```
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
```
- Imports JavaFX classes needed for creating the user interface, including:
  - Application: The base class for JavaFX applications
  - Scene and Stage: Core components for creating windows
  - Insets and Pos: For layout positioning and spacing
  - Various controls (buttons, text fields, etc.)
  - Layout containers (VBox, HBox, etc.)
  - Image handling classes

Lines 22-26:
```
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
```
- Imports standard Java classes for:
  - File input/output (InputStream)
  - SQL database operations (SQLException)
  - Date handling (LocalDate, DateTimeFormatter)
  - Collections (List)

## Class Declaration and Fields (Lines 38-44)

Line 38: `public class Projectv5 extends Application {`
- Declares the Projectv5 class that extends JavaFX's Application class, making it a JavaFX application.

Lines 40-44:
```
// DAO instances
private final CarDAO carDAO = new CarDAO();
private final EmployeeDAO employeeDAO = new EmployeeDAO();
private final CustomerDAO customerDAO = new CustomerDAO();
private final SaleDAO saleDAO = new SaleDAO();
```
- Creates instances of the DAO classes that will be used throughout the application to interact with the database. These are declared as "final" which means they cannot be changed after initialization.

## Methods

### loadImage Method (Lines 54-67)

Lines 54-67:
```
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
```
- Line 54: Declares a private method named "loadImage" that takes a file path and returns an Image object.
- Line 55: Tries to open the image file as an InputStream from the application's resources.
- Lines 56-58: If the file is found, creates and returns a new Image object.
- Lines 59-61: If the file is not found, prints an error message and returns null.
- Lines 62-66: Catches any exceptions that occur, prints error information, and returns null.

### start Method (Lines 75-79)

Lines 75-79:
```
@Override
public void start(Stage primaryStage) {
    showLoginWindow(primaryStage);
    //showMainDashboard(primaryStage);
}
```
- Line 75: The @Override annotation indicates this method is overriding a method from the parent class (Application).
- Line 76: Declares the start method, which is the entry point for JavaFX applications.
- Line 77: Calls the showLoginWindow method to display the login screen.
- Line 78: A commented-out line that would show the main dashboard directly (likely used for testing).

### showLoginWindow Method (Lines 87-273)

Lines 87-91:
```
private void showLoginWindow(Stage primaryStage) {
    // Load CSS
    Scene scene = new Scene(new VBox(), 450, 600);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 87: Declares a private method to show the login window.
- Line 89: Creates a new Scene (the container for all content in a JavaFX window) with a VBox layout and dimensions of 450x600 pixels.
- Line 90: Loads the CSS stylesheet from the resources.
- Line 91: Adds the stylesheet to the scene.

Lines 94-98:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");

// Add a subtle glow effect to the container
mainLayout.setStyle("-fx-effect: dropshadow(gaussian, rgba(52, 152, 219, 0.4), 15, 0, 0, 0);");
```
- Line 94: Creates a BorderPane layout as the main container.
- Line 95: Adds a CSS class to the layout for styling.
- Line 98: Adds a drop shadow effect to the container for visual appeal.

Lines 101-110:
```
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
```
- Lines 101-103: Creates a vertical box for the header with 10 pixels spacing, centers its content, and adds a CSS class.
- Line 106: Loads the logo image using the loadImage method.
- Lines 107-110: If the image loaded successfully, creates an ImageView to display it, sets its width to 150 pixels, and preserves its aspect ratio.

Lines 112-116:
```
// Add a subtle glow effect to the logo
logoView.setEffect(new javafx.scene.effect.Glow(0.3));

header.getChildren().add(logoView);
```
- Line 113: Adds a glow effect to the logo image for visual appeal.
- Line 115: Adds the logo image view to the header container.

Lines 118-126:
```
// Title and subtitle
Label title = new Label("Platinum Auto Japan");
title.getStyleClass().add("dashboard-title");

Label subtitle = new Label("Please login to continue");
subtitle.getStyleClass().add("dashboard-subtitle");

header.getChildren().addAll(title, subtitle);
mainLayout.setTop(header);
```
- Lines 119-120: Creates a title label with the company name and applies a CSS style.
- Lines 122-123: Creates a subtitle label with login instructions and applies a CSS style.
- Line 125: Adds both the title and subtitle labels to the header container.
- Line 126: Places the header at the top of the main layout.

Lines 128-136:
```
// Login form
VBox loginForm = new VBox(15);
loginForm.setAlignment(Pos.CENTER);
loginForm.setPadding(new Insets(20));
loginForm.getStyleClass().add("form-container");

// Add a subtle border glow to the form
loginForm.setStyle("-fx-border-color: rgba(52, 152, 219, 0.5); -fx-border-width: 1px; -fx-border-radius: 5px;");
```
- Lines 129-132: Creates a vertical box for the login form with 15 pixels spacing, centers its content, adds padding, and applies a CSS style.
- Line 135: Adds a subtle blue border to the form with rounded corners.

Lines 137-156:
```
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
```
- Lines 138-139: Creates a horizontal box for the username field with 10 pixels spacing and left-aligns its content.
- Lines 141-144: Creates a label and text field for the username, sets placeholder text, and sets the width.
- Lines 147-153: Adds a focus listener to the username field that changes its border color when focused/unfocused.
- Line 155: Adds the username label to the username box (note: the text field is added directly to the form later).

Lines 157-176:
```
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
```
- Lines 158-159: Creates a horizontal box for the password field with 10 pixels spacing and left-aligns its content.
- Lines 161-164: Creates a label and password field, sets placeholder text, and sets the width.
- Lines 167-173: Adds a focus listener to the password field that changes its border color when focused/unfocused.
- Line 175: Adds the password label to the password box (note: the password field is added directly to the form later).

Lines 177-184:
```
// Login button with hover effect
Button loginButton = new Button("Login");
loginButton.getStyleClass().add("success-button");
loginButton.setPrefWidth(250);

// Add a subtle shadow effect to the button
loginButton.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);");
```
- Lines 178-180: Creates a login button, applies a CSS style, and sets its width.
- Line 183: Adds a subtle shadow effect to the button for visual depth.

Lines 185-193:
```
// Error message label
Label errorLabel = new Label("");
errorLabel.setTextFill(javafx.scene.paint.Color.RED);
errorLabel.setVisible(false);

// Create a separator for visual appeal
Separator separator = new Separator();
separator.setPrefWidth(250);
separator.setOpacity(0.3);
```
- Lines 186-188: Creates a label for error messages, sets its color to red, and initially hides it.
- Lines 190-193: Creates a horizontal separator line for visual separation, sets its width, and makes it partially transparent.

Lines 195-202:
```
// Add login form elements
loginForm.getChildren().addAll(
    usernameLabel, usernameField,
    passwordLabel, passwordField,
    separator,
    errorLabel,
    loginButton
);
```
- Lines 195-202: Adds all the form elements (labels, fields, separator, error label, and button) to the login form container.

Lines 204-240:
```
// Set login button action with animation
loginButton.setOnAction(e -> {
    String username = usernameField.getText().trim();
    String password = passwordField.getText().trim();

    // Simple validation - in a real app, you would check against a database
    if (username.isEmpty() || password.isEmpty()) {
        errorLabel.setText("Username and password are required");
        errorLabel.setVisible(true);

        // Shake animation for error
        javafx.animation.TranslateTransition shake = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(50), errorLabel);
        shake.setFromX(0);
        shake.setByX(10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        shake.play();

    } else if (!username.equals("admin") || !password.equals("admin")) {
        errorLabel.setText("Invalid username or password");
        errorLabel.setVisible(true);
        // Shake animation for error
        javafx.animation.TranslateTransition shake = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(50), errorLabel);
        shake.setFromX(0);
        shake.setByX(10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        shake.play();
    } else {
        // Success animation - fade out login form and show dashboard
        javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(javafx.util.Duration.millis(600), mainLayout);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> showMainDashboard(primaryStage));
        fadeOut.play();
    }
});
```
- Line 205: Sets an action to be performed when the login button is clicked.
- Lines 206-207: Gets the entered username and password, removing any leading/trailing spaces.
- Lines 210-220: Checks if either field is empty, and if so, displays an error message and plays a shake animation.
- Lines 222-231: Checks if the username and password don't match "admin", and if so, displays an error message and plays a shake animation.
- Lines 232-238: If login is successful, creates and plays a fade-out animation, and when it finishes, calls the showMainDashboard method.
- Line 240: Closes the lambda expression for the button action.

Lines 242-247:
```
// Add keyboard event handler for Enter key
scene.setOnKeyPressed(event -> {
    if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
        loginButton.fire();
    }
});
```
- Lines 243-246: Adds a key event handler to the scene that triggers the login button when the Enter key is pressed.

Lines 249-259:
```
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
```
- Line 250: Places the login form in the center of the main layout.
- Lines 253-258: Creates a footer with a copyright label, centers it, adds padding, and applies a CSS style.
- Line 259: Places the footer at the bottom of the main layout.

Lines 261-272:
```
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
```
- Line 262: Sets the main layout as the root of the scene.
- Line 263: Sets the scene on the primary stage.
- Line 264: Sets the title of the window.
- Lines 267-270: Creates and plays a fade-in animation when the login window appears.
- Line 272: Displays the window.

### showMainDashboard Method (Lines 281-362)

Lines 281-285:
```
private void showMainDashboard(Stage primaryStage) {
    // Load CSS
    Scene scene = new Scene(new VBox(), 850, 1000);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 281: Declares a private method to show the main dashboard.
- Line 283: Creates a new Scene with a VBox layout and dimensions of 850x1000 pixels.
- Lines 284-285: Loads and adds the CSS stylesheet to the scene.

Lines 287-290:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");
```
- Lines 288-289: Creates a BorderPane layout as the main container and adds a CSS class for styling.

Lines 291-313:
```
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
```
- Lines 292-294: Creates a vertical box for the header with 10 pixels spacing, centers its content, and adds a CSS class.
- Lines 297-303: Loads the logo image, creates an ImageView to display it, sets its width to 200 pixels, preserves its aspect ratio, and adds it to the header.
- Lines 306-310: Creates title and subtitle labels with the company name and slogan, and applies CSS styles.
- Lines 312-313: Adds the title and subtitle to the header and places the header at the top of the main layout.

Lines 315-329:
```
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
```
- Lines 316-320: Creates a GridPane for the menu buttons, centers it, sets horizontal and vertical gaps between cells, and adds padding.
- Lines 323-328: Creates six dashboard buttons with text and icons using the createDashboardButton method.

Lines 330-336:
```
// Set button actions
viewStock.setOnAction(e -> showStockWindow(primaryStage));
employeeData.setOnAction(e -> showEmployeeDataWindow(primaryStage));
customerData.setOnAction(e -> showCustomerDataWindow(primaryStage));
sales.setOnAction(e -> showSalesWindow(primaryStage));
report.setOnAction(e -> showReportWindow(primaryStage));
commission.setOnAction(e -> showCommissionWindow(primaryStage));
```
- Lines 331-336: Sets actions for each button to show the corresponding window when clicked.

Lines 338-346:
```
// Add buttons to grid (2 columns, 3 rows)
menuGrid.add(viewStock, 0, 0);
menuGrid.add(employeeData, 1, 0);
menuGrid.add(customerData, 0, 1);
menuGrid.add(sales, 1, 1);
menuGrid.add(report, 0, 2);
menuGrid.add(commission, 1, 2);

mainLayout.setCenter(menuGrid);
```
- Lines 339-344: Adds the buttons to the grid in a 2x3 layout (2 columns, 3 rows).
- Line 346: Places the menu grid in the center of the main layout.

Lines 348-355:
```
// Footer
HBox footer = new HBox();
footer.setAlignment(Pos.CENTER);
footer.setPadding(new Insets(10));
Label footerLabel = new Label("© 2025 Platinum Auto Japan");
footerLabel.getStyleClass().add("dashboard-subtitle");
footer.getChildren().add(footerLabel);
mainLayout.setBottom(footer);
```
- Lines 349-354: Creates a footer with a copyright label, centers it, adds padding, and applies a CSS style.
- Line 355: Places the footer at the bottom of the main layout.

Lines 357-361:
```
// Set the scene
scene.setRoot(mainLayout);
primaryStage.setScene(scene);
primaryStage.setTitle("Dashboard - Platinum Auto Japan");
primaryStage.show();
```
- Line 358: Sets the main layout as the root of the scene.
- Line 359: Sets the scene on the primary stage.
- Line 360: Sets the title of the window.
- Line 361: Displays the window.

### createDashboardButton Method (Lines 371-407)

Lines 371-375:
```
private Button createDashboardButton(String text, String iconPath) {
    // Create button without text to avoid duplication
    Button button = new Button();
    button.setPrefSize(200, 120);
```
- Line 371: Declares a private method to create a dashboard button with text and an icon.
- Lines 373-374: Creates a new Button without text and sets its preferred size to 200x120 pixels.

Lines 376-379:
```
// Create a VBox to hold the icon and text vertically
VBox content = new VBox(10);
content.setAlignment(Pos.CENTER);
```
- Lines 377-378: Creates a vertical box with 10 pixels spacing to hold the icon and text, and centers its content.

Lines 380-395:
```
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
```
- Line 381: Loads the icon image using the loadImage method.
- Lines 382-387: If the image loaded successfully, creates an ImageView to display it and sets its size to 64x64 pixels.
- Lines 389-393: Creates a StackPane container for the icon, adds the icon to it, applies a semi-transparent white background with rounded corners, and adds padding.
- Line 394: Adds the icon container to the content VBox.

Lines 397-406:
```
// Add text label
Label label = new Label(text);
label.setStyle("-fx-text-fill: white;"); // Ensure text is visible on dark background
content.getChildren().add(label);

// Set the VBox as the graphic for the button
button.setGraphic(content);
button.setContentDisplay(ContentDisplay.TOP);

return button;
```
- Lines 398-400: Creates a label with the button text, sets its text color to white, and adds it to the content VBox.
- Lines 403-404: Sets the content VBox as the graphic for the button and positions it at the top.
- Line 406: Returns the created button.

### showStockWindow Method (Lines 415-768)

Lines 415-419:
```
private void showStockWindow(Stage primaryStage) {
    // Load CSS if not already loaded
    Scene scene = new Scene(new VBox(), 800, 900);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 415: Declares a private method to show the car stock management window.
- Line 417: Creates a new Scene with a VBox layout and dimensions of 800x900 pixels.
- Lines 418-419: Loads and adds the CSS stylesheet to the scene.

Lines 421-423:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");
```
- Lines 422-423: Creates a BorderPane layout as the main container and adds a CSS class for styling.

Lines 425-432:
```
// Header with title and back button
HBox header = new HBox(15);
header.setAlignment(Pos.CENTER_LEFT);
header.setPadding(new Insets(15));

Button backBtn = new Button("← Back");
backBtn.getStyleClass().add("back-button");
backBtn.setOnAction(e -> showMainDashboard(primaryStage));
```
- Lines 426-428: Creates a horizontal box for the header with 15 pixels spacing, left-aligns its content, and adds padding.
- Lines 430-432: Creates a back button with an arrow symbol, applies a CSS style, and sets its action to show the main dashboard when clicked.

Lines 434-447:
```
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
```
- Line 435: Loads the stock icon image using the loadImage method.
- Lines 436-440: If the image loaded successfully, creates an ImageView to display it, sets its size to 40x40 pixels, and adds it to the header.
- Lines 443-444: Creates a title label for the window and applies a CSS style.
- Line 446: Adds the back button and title to the header.
- Line 447: Places the header at the top of the main layout.

Lines 449-467:
```
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
```
- Lines 450-452: Creates a horizontal box for the action buttons with 15 pixels spacing, centers its content, and adds padding.
- Lines 454-455: Creates an "Add Car" button and applies a CSS style.
- Lines 457-458: Creates a "Delete Car" button and applies a CSS style.
- Lines 461-462: Creates a "Sell Car" button and applies a CSS style.
- Lines 464-465: Creates a "Show All Cars" button and applies a CSS style.
- Line 467: Adds all the buttons to the action buttons container.

Lines 469-476:
```
// Text area for displaying cars
TextArea stockArea = new TextArea();
stockArea.setEditable(false);
stockArea.setPrefHeight(700);
stockArea.setPrefWidth(600);
stockArea.setFont(javafx.scene.text.Font.font("Monospaced", 12));
stockArea.setWrapText(false);
```
- Line 470: Creates a text area for displaying car information.
- Line 471: Makes the text area non-editable (read-only).
- Lines 472-473: Sets the preferred height and width of the text area.
- Line 474: Sets the font to Monospaced with size 12 for better alignment of tabular data.
- Line 475: Disables text wrapping to maintain column alignment.

Lines 477-486:
```
// Main content
VBox content = new VBox(15);
content.setPadding(new Insets(15));
content.getChildren().addAll(actionButtons, stockArea);
mainLayout.setCenter(content);

// Set the scene
scene.setRoot(mainLayout);
primaryStage.setScene(scene);
primaryStage.setTitle("Car Stock Management - Platinum Auto Japan");
```
- Lines 478-480: Creates a vertical box for the main content with 15 pixels spacing, adds padding, and adds the action buttons and stock area to it.
- Line 481: Places the content in the center of the main layout.
- Lines 484-486: Sets the main layout as the root of the scene, sets the scene on the primary stage, and sets the title of the window.

Lines 488-507:
```
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
```
- Line 489: Sets an action to be performed when the "Sell Car" button is clicked.
- Line 490: Creates a new stage (window) for the sell car form.
- Lines 491-494: Creates a grid pane for the form, adds padding, and sets vertical and horizontal gaps between cells.
- Lines 497-498: Creates a text field for the sale ID and makes it non-editable since it will be auto-generated.
- Lines 501-507: Tries to auto-generate the next sale ID using the saleDAO, sets it in the text field, and catches any SQL exceptions that occur.

Lines 509-536:
```
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
```
- Lines 509-512: Creates combo boxes for selecting a car, customer, and employee, and a text field for the sale amount.
- Lines 515-529: Tries to load lists of cars, customers, and employees from the database and populate the combo boxes with their IDs and names.
- Lines 530-536: Catches any SQL exceptions that occur, displays an error alert with details, and waits for the user to acknowledge it.

Lines 538-561:
```
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
        if (carComboBox.getValue() == null || 
            customerComboBox.getValue() == null || 
            employeeComboBox.getValue() == null || 
            amountField.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all fields to complete the sale.");
            alert.showAndWait();
            return;
        }
```
- Lines 539-543: Adds rows to the form grid with labels and their corresponding input fields for sale ID, car, customer, employee, and sale amount.
- Line 545: Creates a "Complete Sale" button for saving the sale.
- Lines 547-553: Sets an action to be performed when the "Complete Sale" button is clicked, which begins with validation to check if any required fields are empty.
- Lines 555-560: If validation fails, displays an error alert with details and returns from the method.

Lines 563-580:
```
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
```
- Lines 564-566: Extracts the IDs from the selected values in the combo boxes by splitting the strings at " - " and taking the first part.
- Lines 569-570: Gets the current date and formats it as an ISO local date string (YYYY-MM-DD).
- Lines 573-580: Creates a new Sale object with the extracted data, including the sale ID, car ID, customer ID, employee ID, sale amount (converted to a double), and formatted date.

Lines 582-587:
```
// Save the sale to the database
try {
    saleDAO.addSale(sale);
} catch (SQLException ex) {
    throw new RuntimeException("Database error while saving sale: " + ex.getMessage(), ex);
}
```
- Lines 583-584: Tries to add the sale to the database using the saleDAO.
- Lines 585-587: Catches any SQL exceptions that occur and throws a runtime exception with details.

Lines 589-602:
```
// Show success message
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Success");
alert.setHeaderText("Sale Completed");
alert.setContentText("Vehicle " + carComboBox.getValue() + 
                     " has been sold to " + customerComboBox.getValue() + 
                     " by " + employeeComboBox.getValue());
alert.showAndWait();

sellStage.close();

// Refresh the car list to reflect the changes
showAllCars.fire();
```
- Lines 590-596: Creates and displays an information alert with a success message that includes details of the sale.
- Line 598: Closes the sell car form window.
- Line 601: Programmatically clicks the "Show All Cars" button to refresh the car list and reflect the changes.

Lines 603-609:
```
} catch (Exception ex) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Sale Error");
    alert.setContentText("Error completing sale: " + ex.getMessage());
    alert.showAndWait();
}
```
- Lines 603-609: Catches any exceptions that occur during the sale process, displays an error alert with details, and waits for the user to acknowledge it.

Lines 612-629:
```
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
```
- Lines 612-614: Creates a horizontal box for the button, right-aligns it, and adds the save button to it.
- Lines 616-622: Creates a vertical box for the form content, adds padding, and adds a title label, the form, and the button box to it.
- Lines 624-625: Creates a new scene with the form content and adds the CSS stylesheet to it.
- Lines 627-629: Sets the scene on the sell stage, sets the title of the window, and displays the window.

Lines 632-714:
```
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
```
- Line 632: Sets an action to be performed when the "Add Car" button is clicked.
- Line 633: Creates a new stage (window) for the add car form.
- Lines 634-637: Creates a grid pane for the form, adds padding, and sets vertical and horizontal gaps between cells.
- Lines 639-648: Creates text fields for each car property (ID, model, variant, price, mileage, color, type, weight, number plate, and chassis number).
- Lines 650-659: Adds rows to the form grid with labels and their corresponding text fields for each car property.
- Line 661: Creates a "Save" button for saving the car.
- Lines 663-691: Sets an action to be performed when the "Save" button is clicked:
  - Lines 666-677: Creates a new Car object with the data from the form fields, converting numeric values to doubles.
  - Lines 680-681: Adds the car to the database using the carDAO and closes the form window.
  - Lines 682-685: Catches number format exceptions (if non-numeric values are entered for numeric fields) and displays an error alert.
  - Lines 686-690: Catches any other exceptions, prints the stack trace, and displays an error alert with details.
- Lines 694-695: Applies CSS styles to the form and save button.
- Lines 698-699: Creates a title label for the form and applies a CSS style.
- Lines 701-704: Creates a vertical box for the form layout, adds the title, form, and save button to it, applies a CSS style, adds padding, and centers its content.
- Lines 707-709: Creates a new scene with the form layout, sets its size to 500x600 pixels, and adds the CSS stylesheet to it.
- Lines 711-713: Sets the scene on the add stage, sets the title of the window, and displays the window.

Lines 716-736:
```
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
```
- Line 716: Sets an action to be performed when the "Delete Car" button is clicked.
- Lines 717-718: Creates a text input dialog and sets its header text.
- Line 719: Shows the dialog and waits for the user to enter a car ID, then performs an action if a value is present.
- Lines 720-734: Tries to delete the car with the entered ID:
  - Line 722: Deletes the car using the carDAO and gets the number of affected rows.
  - Lines 723-725: If no rows were affected (no car found with the ID), displays an information alert.
  - Lines 726-728: If the car was deleted successfully, displays an information alert.
  - Lines 730-733: Catches any exceptions, prints the stack trace, and displays an error alert with details.

Lines 738-767:
```
showAllCars.setOnAction(e -> {
    stockArea.clear();
    try {
        // Use the DAO to get all cars
        List<Car> cars = carDAO.getAll();

        if (cars.isEmpty()) {
            stockArea.setText("No cars found in the database.");
            return;
        }

        // For tabular display, we need to handle the header differently
        // Get the first car's toString which includes the header and separator
        String firstCarString = cars.get(0).toString();
        // Split by newline to separate header, separator, and data
        String[] parts = firstCarString.split("\n");

        // Append header and separator
        stockArea.appendText(parts[0] + "\n" + parts[1] + "\n");

        // Now append just the data row for each car
        for (Car car : cars) {
            String[] carParts = car.toString().split("\n");
            stockArea.appendText(carParts[2] + "\n");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        stockArea.setText("Error loading cars: " + ex.getMessage());
    }
});
```
- Line 738: Sets an action to be performed when the "Show All Cars" button is clicked.
- Line 739: Clears the text area.
- Lines 740-766: Tries to load and display all cars:
  - Line 742: Gets all cars from the database using the carDAO.
  - Lines 744-747: If no cars are found, displays a message and returns.
  - Lines 751-753: Gets the string representation of the first car (which includes a header and separator) and splits it by newline.
  - Line 756: Appends the header and separator to the text area.
  - Lines 759-762: For each car, splits its string representation by newline and appends just the data row to the text area.
  - Lines 763-766: Catches any exceptions, prints the stack trace, and displays an error message in the text area.

This completes the explanation of the showStockWindow method.

### showEmployeeDataWindow Method (Lines 776-918)

Lines 776-780:
```
private void showEmployeeDataWindow(Stage primaryStage) {
    // Load CSS
    Scene scene = new Scene(new VBox(), 800, 900);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 776: Declares a private method to show the employee data management window.
- Line 778: Creates a new Scene with a VBox layout and dimensions of 800x900 pixels.
- Lines 779-780: Loads and adds the CSS stylesheet to the scene.

Lines 782-784:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");
```
- Lines 783-784: Creates a BorderPane layout as the main container and adds a CSS class for styling.

Lines 786-808:
```
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
```
- Lines 787-789: Creates a horizontal box for the header with 15 pixels spacing, left-aligns its content, and adds padding.
- Lines 791-793: Creates a back button with an arrow symbol, applies a CSS style, and sets its action to show the main dashboard when clicked.
- Lines 796-802: Loads the employee icon image, creates an ImageView to display it, sets its size to 40x40 pixels, and adds it to the header.
- Lines 804-805: Creates a title label for the window and applies a CSS style.
- Lines 807-808: Adds the back button and title to the header and places the header at the top of the main layout.

Lines 810-813:
```
// Main content
VBox content = new VBox(20);
content.setPadding(new Insets(15));
```
- Lines 811-812: Creates a vertical box for the main content with 20 pixels spacing and adds padding.

Lines 814-843:
```
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
```
- Lines 815-816: Creates a label for the add employee form and applies a CSS style.
- Lines 818-821: Creates a grid pane for the form, sets horizontal and vertical gaps between cells, and adds padding.
- Lines 824-831: Creates labels and text fields for employee ID, name, and salary.
- Lines 833-834: Creates an "Add Employee" button and applies a CSS style.
- Lines 837-843: Adds the form elements to the grid in their respective positions.

Lines 845-847:
```
// Status label for feedback
Label statusLabel = new Label("");
statusLabel.getStyleClass().add("status-label");
```
- Lines 846-847: Creates a label for status messages and applies a CSS style.

Lines 849-855:
```
// Text area for displaying employees
Label employeeListLabel = new Label("Employee Details:");
employeeListLabel.getStyleClass().add("section-header");

TextArea area = new TextArea();
area.setEditable(false);
area.setPrefHeight(300);
```
- Lines 850-851: Creates a label for the employee list and applies a CSS style.
- Lines 853-855: Creates a text area for displaying employee details, makes it non-editable, and sets its preferred height.

Lines 857-901:
```
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
```
- Line 858: Sets an action to be performed when the "Add Employee" button is clicked.
- Lines 860-863: Gets the entered employee ID, name, and salary, removing any leading/trailing spaces.
- Lines 865-869: Checks if any fields are empty, and if so, displays an error message and returns.
- Lines 871-879: Tries to parse the salary as a double, and if it fails, displays an error message and returns.
- Lines 882-883: Creates a new Employee object with the entered data and adds it to the database using the employeeDAO.
- Lines 886-888: Clears the form fields.
- Lines 890-891: Updates the status label with a success message and removes the error styling.
- Line 894: Refreshes the employee list in the text area.
- Lines 896-900: Catches any exceptions that occur, prints the stack trace, and displays an error message.

Lines 903-909:
```
// Add all components to content
content.getChildren().addAll(
    addEmployeeLabel, formGrid, statusLabel, 
    new Separator(), employeeListLabel, area
);

mainLayout.setCenter(content);
```
- Lines 904-907: Adds all components to the content container, including the add employee label, form grid, status label, a separator, employee list label, and text area.
- Line 909: Places the content in the center of the main layout.

Lines 911-917:
```
// Initial load of employee list
refreshEmployeeList(area);

// Set the scene
scene.setRoot(mainLayout);
primaryStage.setScene(scene);
primaryStage.setTitle("Employee Data - Platinum Auto Japan");
```
- Line 912: Calls the refreshEmployeeList method to initially load the employee list.
- Lines 915-917: Sets the main layout as the root of the scene, sets the scene on the primary stage, and sets the title of the window.

### refreshEmployeeList Method (Lines 925-949)

Lines 925-928:
```
private void refreshEmployeeList(TextArea area) {
    try {
        // Clear existing content
        area.clear();
```
- Line 925: Declares a private helper method to refresh the employee list in a text area.
- Line 928: Clears the existing content of the text area.

Lines 930-944:
```
// Use the DAO to get all employees
List<Employee> employees = employeeDAO.getAll();

if (employees.isEmpty()) {
    area.setText("No employees found in the database.");
} else {
    // Display each employee in the text area
    for (Employee employee : employees) {
        area.appendText("- Employee:\n" + 
            "ID: " + employee.getId() + "\n" +
            "Name: " + employee.getName() + "\n" +
            "Salary: " + employee.getSalary() + "\n\n" +
            "----------------------------------------\n\n");
    }
}
```
- Line 931: Gets all employees from the database using the employeeDAO.
- Lines 933-934: If no employees are found, displays a message.
- Lines 936-943: For each employee, appends their details (ID, name, and salary) to the text area, along with formatting and separators.

Lines 945-948:
```
} catch (Exception e) {
    e.printStackTrace();
    area.setText("Failed to load employee data: " + e.getMessage());
}
```
- Lines 945-948: Catches any exceptions that occur, prints the stack trace, and displays an error message in the text area.

### showCustomerDataWindow Method (Lines 957-1096)

Lines 957-961:
```
private void showCustomerDataWindow(Stage primaryStage) {
    // Load CSS
    Scene scene = new Scene(new VBox(), 800, 900);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 957: Declares a private method to show the customer data management window.
- Line 959: Creates a new Scene with a VBox layout and dimensions of 800x900 pixels.
- Lines 960-961: Loads and adds the CSS stylesheet to the scene.

Lines 963-965:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");
```
- Lines 964-965: Creates a BorderPane layout as the main container and adds a CSS class for styling.

Lines 967-989:
```
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
```
- Lines 968-970: Creates a horizontal box for the header with 15 pixels spacing, left-aligns its content, and adds padding.
- Lines 972-974: Creates a back button with an arrow symbol, applies a CSS style, and sets its action to show the main dashboard when clicked.
- Lines 977-983: Loads the customer icon image, creates an ImageView to display it, sets its size to 40x40 pixels, and adds it to the header.
- Lines 985-986: Creates a title label for the window and applies a CSS style.
- Lines 988-989: Adds the back button and title to the header and places the header at the top of the main layout.

Lines 991-993:
```
// Main content
VBox content = new VBox(20);
content.setPadding(new Insets(15));
```
- Lines 992-993: Creates a vertical box for the main content with 20 pixels spacing and adds padding.

Lines 995-1029:
```
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
```
- Lines 996-997: Creates a label for the add customer form and applies a CSS style.
- Lines 999-1002: Creates a grid pane for the form, sets horizontal and vertical gaps between cells, and adds padding.
- Lines 1005-1016: Creates labels and text fields for customer ID, name, phone, and email.
- Lines 1018-1019: Creates an "Add Customer" button and applies a CSS style.
- Lines 1022-1029: Adds the form elements to the grid in their respective positions.

Lines 1031-1033:
```
// Status label for feedback
Label statusLabel = new Label("");
statusLabel.getStyleClass().add("status-label");
```
- Lines 1032-1033: Creates a label for status messages and applies a CSS style.

Lines 1035-1041:
```
// Text area for displaying customers
Label customerListLabel = new Label("Customer Details:");
customerListLabel.getStyleClass().add("section-header");

TextArea area = new TextArea();
area.setEditable(false);
area.setPrefHeight(300);
```
- Lines 1036-1037: Creates a label for the customer list and applies a CSS style.
- Lines 1039-1041: Creates a text area for displaying customer details, makes it non-editable, and sets its preferred height.

Lines 1043-1079:
```
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
```
- Line 1044: Sets an action to be performed when the "Add Customer" button is clicked.
- Lines 1046-1050: Gets the entered customer ID, name, phone, and email, removing any leading/trailing spaces.
- Lines 1052-1056: Checks if any fields are empty, and if so, displays an error message and returns.
- Lines 1059-1060: Creates a new Customer object with the entered data and adds it to the database using the customerDAO.
- Lines 1063-1066: Clears the form fields.
- Lines 1068-1069: Updates the status label with a success message and removes the error styling.
- Line 1072: Refreshes the customer list in the text area.
- Lines 1074-1078: Catches any exceptions that occur, prints the stack trace, and displays an error message.

Lines 1081-1087:
```
// Add all components to content
content.getChildren().addAll(
    addCustomerLabel, formGrid, statusLabel, 
    new Separator(), customerListLabel, area
);

mainLayout.setCenter(content);
```
- Lines 1082-1085: Adds all components to the content container, including the add customer label, form grid, status label, a separator, customer list label, and text area.
- Line 1087: Places the content in the center of the main layout.

Lines 1089-1095:
```
// Initial load of customer list
refreshCustomerList(area);

// Set the scene
scene.setRoot(mainLayout);
primaryStage.setScene(scene);
primaryStage.setTitle("Customer Data - Platinum Auto Japan");
```
- Line 1090: Calls the refreshCustomerList method to initially load the customer list.
- Lines 1093-1095: Sets the main layout as the root of the scene, sets the scene on the primary stage, and sets the title of the window.

### refreshCustomerList Method (Lines 1103-1128)

Lines 1103-1106:
```
private void refreshCustomerList(TextArea area) {
    try {
        // Clear existing content
        area.clear();
```
- Line 1103: Declares a private helper method to refresh the customer list in a text area.
- Line 1106: Clears the existing content of the text area.

Lines 1108-1123:
```
// Use the DAO to get all customers
List<Customer> customers = customerDAO.getAll();

if (customers.isEmpty()) {
    area.setText("No customers found in the database.");
} else {
    // Display each customer in the text area
    for (Customer customer : customers) {
        area.appendText("- Customer:\n" + 
            "ID: " + customer.getId() + "\n" +
            "Name: " + customer.getName() + "\n" +
            "Phone: " + customer.getPhone() + "\n" +
            "Email: " + customer.getEmail() + "\n\n" +
            "----------------------------------------\n\n");
    }
}
```
- Line 1109: Gets all customers from the database using the customerDAO.
- Lines 1111-1112: If no customers are found, displays a message.
- Lines 1114-1122: For each customer, appends their details (ID, name, phone, and email) to the text area, along with formatting and separators.

Lines 1124-1127:
```
} catch (Exception e) {
    e.printStackTrace();
    area.setText("Failed to load customer data: " + e.getMessage());
}
```
- Lines 1124-1127: Catches any exceptions that occur, prints the stack trace, and displays an error message in the text area.

### showSalesWindow Method (Lines 1136-1427)

Lines 1136-1140:
```
private void showSalesWindow(Stage primaryStage) {
    // Load CSS
    Scene scene = new Scene(new VBox(), 800, 900);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 1136: Declares a private method to show the sales management window.
- Line 1138: Creates a new Scene with a VBox layout and dimensions of 800x900 pixels.
- Lines 1139-1140: Loads and adds the CSS stylesheet to the scene.

Lines 1142-1144:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");
```
- Lines 1143-1144: Creates a BorderPane layout as the main container and adds a CSS class for styling.

Lines 1146-1168:
```
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
```
- Lines 1147-1149: Creates a horizontal box for the header with 15 pixels spacing, left-aligns its content, and adds padding.
- Lines 1151-1153: Creates a back button with an arrow symbol, applies a CSS style, and sets its action to show the main dashboard when clicked.
- Lines 1156-1162: Loads the sales icon image, creates an ImageView to display it, sets its size to 40x40 pixels, and adds it to the header.
- Lines 1164-1165: Creates a title label for the window and applies a CSS style.
- Lines 1167-1168: Adds the back button and title to the header and places the header at the top of the main layout.

Lines 1170-1173:
```
// Main content
VBox content = new VBox(20);
content.setPadding(new Insets(20));
content.setAlignment(Pos.TOP_CENTER);
```
- Lines 1171-1173: Creates a vertical box for the main content with 20 pixels spacing, adds padding, and aligns its content to the top center.

Lines 1175-1238:
```
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
```
- Lines 1176-1178: Creates a vertical box for the new sale section, applies a CSS style, and adds padding.
- Lines 1180-1182: Creates a label for the new sale section, applies a CSS style, and adds it to the new sale section.
- Lines 1185-1188: Creates a grid pane for the sale form, sets horizontal and vertical gaps between cells, and adds padding.
- Lines 1191-1193: Creates a label and combo box for car selection and sets the combo box width.
- Lines 1196-1198: Creates a label and combo box for customer selection and sets the combo box width.
- Lines 1201-1203: Creates a label and combo box for employee selection and sets the combo box width.
- Lines 1206-1208: Creates a label and text field for the sale amount and sets the text field width.
- Lines 1211-1213: Creates a label and date picker for the sale date, initializes it with the current date, and sets its width.
- Lines 1216-1225: Adds the form elements to the grid in their respective positions.
- Line 1227: Adds the form to the new sale section.
- Lines 1230-1232: Creates a "Create Sale" button, applies a CSS style, and sets its width.
- Lines 1234-1236: Creates a horizontal box for the button, centers it, and adds the button to it.
- Line 1238: Adds the button box to the new sale section.

Lines 1240-1290:
```
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
amountColumn.setCellValueFactory(data -> 
    new javafx.beans.property.SimpleDoubleProperty(data.getValue().getAmount()).asObject());

TableColumn<Sale, String> dateColumn = new TableColumn<>("Date");
dateColumn.setCellValueFactory(data -> 
    new javafx.beans.property.SimpleStringProperty(data.getValue().getDate()));

// Add columns to table
salesTable.getColumns().addAll(idColumn, carColumn, customerColumn, amountColumn, dateColumn);

recentSalesSection.getChildren().add(salesTable);
```
- Lines 1241-1243: Creates a vertical box for the recent sales section, applies a CSS style, and adds padding.
- Lines 1245-1247: Creates a label for the recent sales section, applies a CSS style, and adds it to the recent sales section.
- Lines 1250-1251: Creates a table view for displaying sales and sets its preferred height.
- Lines 1254-1255: Creates an ID column for the table and sets its cell value factory to get the sale ID.
- Lines 1257-1266: Creates a car column for the table and sets its cell value factory to get the car model and variant from the database using the car ID.
- Lines 1268-1277: Creates a customer column for the table and sets its cell value factory to get the customer name from the database using the customer ID.
- Lines 1279-1281: Creates an amount column for the table and sets its cell value factory to get the sale amount.
- Lines 1283-1285: Creates a date column for the table and sets its cell value factory to get the sale date.
- Line 1288: Adds all columns to the table.
- Line 1290: Adds the table to the recent sales section.

Lines 1292-1299:
```
// Add sections to content
content.getChildren().addAll(newSaleSection, recentSalesSection);

// Add scrolling for the entire content
ScrollPane contentScrollPane = new ScrollPane(content);
contentScrollPane.setFitToWidth(true);
contentScrollPane.setFitToHeight(true);
mainLayout.setCenter(contentScrollPane);
```
- Line 1293: Adds the new sale section and recent sales section to the content.
- Lines 1296-1298: Creates a scroll pane for the content, makes it fit to width and height, and places it in the center of the main layout.

Lines 1301-1322:
```
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
```
- Line 1301: Starts a try block for database operations.
- Line 1303: Gets all cars from the database.
- Lines 1304-1321: For each car, checks if it has already been sold by comparing its ID with the car IDs in all sales, and if not, adds it to the car combo box.

Lines 1324-1334:
```
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
```
- Lines 1325-1328: Gets all customers from the database and adds them to the customer combo box.
- Lines 1331-1334: Gets all employees from the database and adds them to the employee combo box.

Lines 1336-1338:
```
// Populate recent sales table
List<Sale> recentSales = saleDAO.getAll();
salesTable.getItems().addAll(recentSales);
```
- Lines 1337-1338: Gets all sales from the database and adds them to the sales table.

Lines 1340-1412:
```
// Create sale button action
createSaleBtn.setOnAction(e -> {
    try {
        // Validate inputs
        if (carComboBox.getValue() == null || customerComboBox.getValue() == null || 
            employeeComboBox.getValue() == null || amountField.getText().isEmpty() || 
            datePicker.getValue() == null) {

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
```
- Line 1341: Sets an action to be performed when the "Create Sale" button is clicked.
- Lines 1343-1354: Validates that all required fields are filled in, and if not, displays an error alert and returns.
- Lines 1357-1359: Extracts the car, customer, and employee IDs from the combo box selections by splitting the strings at " - " and taking the first part.
- Lines 1362-1372: Tries to parse the amount as a double, and if it fails, displays an error alert and returns.
- Line 1375: Formats the selected date as a string in the format "yyyy-MM-dd".
- Line 1378: Generates a sale ID by concatenating "SALE" with a three-digit number based on the current number of sales.
- Lines 1381-1382: Creates a new Sale object with the extracted data and adds it to the database.
- Line 1385: Adds the new sale to the sales table.
- Line 1388: Removes the sold car from the car combo box.
- Lines 1391-1395: Clears the form fields and resets the date picker to the current date.
- Lines 1398-1402: Displays a success alert.
- Lines 1404-1411: Catches any exceptions that occur, prints the stack trace, and displays an error alert.

Lines 1414-1421:
```
} catch (Exception e) {
    e.printStackTrace();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Failed to load data: " + e.getMessage());
    alert.showAndWait();
}
```
- Lines 1414-1421: Catches any exceptions that occur during the database operations, prints the stack trace, and displays an error alert.

Lines 1423-1426:
```
// Set the scene
scene.setRoot(mainLayout);
primaryStage.setScene(scene);
primaryStage.setTitle("Sales Management - Platinum Auto Japan");
```
- Lines 1424-1426: Sets the main layout as the root of the scene, sets the scene on the primary stage, and sets the title of the window.

### saveSalesReportAsTxt Method (Lines 1434-1468)

Lines 1434-1441:
```
private void saveSalesReportAsTxt(String content) {
    try {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Save Sales Report");
        fileChooser.getExtensionFilters().add(
            new javafx.stage.FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialFileName("sales_report.txt");
```
- Line 1434: Declares a private method to save a sales report as a text file, taking the report content as a parameter.
- Line 1436: Creates a new FileChooser dialog for selecting where to save the file.
- Line 1437: Sets the title of the file chooser dialog.
- Lines 1438-1439: Adds an extension filter to only show text files (*.txt) in the dialog.
- Line 1440: Sets the default filename to "sales_report.txt".

Lines 1442-1448:
```
java.io.File file = fileChooser.showSaveDialog(null);

if (file != null) {
    java.io.FileWriter fileWriter = new java.io.FileWriter(file);
    fileWriter.write(content);
    fileWriter.close();
```
- Line 1442: Shows the save dialog and gets the selected file.
- Line 1444: Checks if a file was selected (not null).
- Line 1445: Creates a FileWriter for writing to the selected file.
- Line 1446: Writes the content to the file.
- Line 1447: Closes the file writer to ensure all data is written and resources are released.

Lines 1449-1456:
```
// Show success message
javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
    javafx.scene.control.Alert.AlertType.INFORMATION);
alert.setTitle("Success");
alert.setHeaderText(null);
alert.setContentText("Sales report saved successfully as TXT file!");
alert.showAndWait();
```
- Lines 1450-1451: Creates an information alert to show a success message.
- Line 1452: Sets the title of the alert.
- Line 1453: Sets the header text to null (no header).
- Line 1454: Sets the content text of the alert.
- Line 1455: Shows the alert and waits for the user to acknowledge it.

Lines 1457-1467:
```
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
```
- Line 1457: Catches any exceptions that occur during the file saving process.
- Line 1458: Prints the stack trace for debugging.
- Lines 1461-1466: Creates and shows an error alert with details about the failure.

### saveSalesReportAsPdf Method (Lines 1475-1544)

Lines 1475-1483:
```
private void saveSalesReportAsPdf(String content) {
    try {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Save Sales Report");
        fileChooser.getExtensionFilters().add(
            new javafx.stage.FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("sales_report.pdf");

        java.io.File file = fileChooser.showSaveDialog(null);
```
- Line 1475: Declares a private method to save a sales report as a PDF file, taking the report content as a parameter.
- Line 1477: Creates a new FileChooser dialog for selecting where to save the file.
- Line 1478: Sets the title of the file chooser dialog.
- Lines 1479-1480: Adds an extension filter to only show PDF files (*.pdf) in the dialog.
- Line 1481: Sets the default filename to "sales_report.pdf".
- Line 1483: Shows the save dialog and gets the selected file.

Lines 1485-1494:
```
if (file != null) {
    // Create a new PDF document
    org.apache.pdfbox.pdmodel.PDDocument document = new org.apache.pdfbox.pdmodel.PDDocument();
    org.apache.pdfbox.pdmodel.PDPage page = new org.apache.pdfbox.pdmodel.PDPage();
    document.addPage(page);

    // Create a content stream for adding content to the page
    org.apache.pdfbox.pdmodel.PDPageContentStream contentStream = 
        new org.apache.pdfbox.pdmodel.PDPageContentStream(document, page);
```
- Line 1485: Checks if a file was selected (not null).
- Line 1487: Creates a new PDF document using PDFBox.
- Line 1488: Creates a new page for the document.
- Line 1489: Adds the page to the document.
- Lines 1492-1493: Creates a content stream for adding content to the page.

Lines 1496-1505:
```
// Set font and font size
contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER, 12);

// Start text
contentStream.beginText();
contentStream.newLineAtOffset(50, 700);

// Add title
contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER_BOLD, 16);
contentStream.showText("Sales Report");
contentStream.newLineAtOffset(0, -20);
```
- Line 1496: Sets the font to Courier with size 12.
- Line 1499: Begins text mode in the content stream.
- Line 1500: Sets the starting position for the text (50 points from the left, 700 points from the bottom).
- Line 1503: Changes the font to Courier Bold with size 16 for the title.
- Line 1504: Adds the title "Sales Report" to the page.
- Line 1505: Moves the cursor down 20 points for the next line.

Lines 1508-1515:
```
// Add content line by line
contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER, 10);
float leading = 12;

String[] lines = content.split("\n");
for (String line : lines) {
    contentStream.showText(line);
    contentStream.newLineAtOffset(0, -leading);
}
```
- Line 1508: Changes the font back to Courier with size 10 for the content.
- Line 1509: Sets the line spacing (leading) to 12 points.
- Line 1511: Splits the content into lines by newline characters.
- Lines 1512-1515: For each line, adds it to the page and moves the cursor down by the leading amount.

Lines 1517-1523:
```
// End text
contentStream.endText();
contentStream.close();

// Save the document
document.save(file);
document.close();
```
- Line 1518: Ends text mode in the content stream.
- Line 1519: Closes the content stream.
- Line 1522: Saves the document to the selected file.
- Line 1523: Closes the document to release resources.

Lines 1525-1532:
```
// Show success message
javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
    javafx.scene.control.Alert.AlertType.INFORMATION);
alert.setTitle("Success");
alert.setHeaderText(null);
alert.setContentText("Sales report saved successfully as PDF file!");
alert.showAndWait();
```
- Lines 1526-1527: Creates an information alert to show a success message.
- Line 1528: Sets the title of the alert.
- Line 1529: Sets the header text to null (no header).
- Line 1530: Sets the content text of the alert.
- Line 1531: Shows the alert and waits for the user to acknowledge it.

Lines 1533-1543:
```
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
```
- Line 1533: Catches any exceptions that occur during the PDF creation process.
- Line 1534: Prints the stack trace for debugging.
- Lines 1537-1542: Creates and shows an error alert with details about the failure.

### showReportWindow Method (Lines 1552-1712)

Lines 1552-1559:
```
private void showReportWindow(Stage primaryStage) {
    // Load CSS
    Scene scene = new Scene(new VBox(), 800, 900);
    TextArea area = new TextArea();
    area.setEditable(false);
    area.setPrefHeight(700);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 1552: Declares a private method to show the sales report window.
- Line 1554: Creates a new Scene with a VBox layout and dimensions of 800x900 pixels.
- Lines 1555-1557: Creates a text area for displaying the report, makes it non-editable, and sets its preferred height.
- Lines 1558-1559: Loads and adds the CSS stylesheet to the scene.

Lines 1561-1564:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");
```
- Lines 1562-1563: Creates a BorderPane layout as the main container and adds a CSS class for styling.

Lines 1565-1587:
```
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
```
- Lines 1566-1568: Creates a horizontal box for the header with 15 pixels spacing, left-aligns its content, and adds padding.
- Lines 1570-1572: Creates a back button with an arrow symbol, applies a CSS style, and sets its action to show the main dashboard when clicked.
- Lines 1575-1581: Loads the report icon image, creates an ImageView to display it, sets its size to 40x40 pixels, and adds it to the header.
- Lines 1583-1584: Creates a title label for the window and applies a CSS style.
- Lines 1586-1587: Adds the back button and title to the header and places the header at the top of the main layout.

Lines 1589-1599:
```
// Text area for displaying report
TextArea area1 = new TextArea();
area1.setEditable(false);
area1.setPrefHeight(400);
area1.setStyle("-fx-font-family: 'Courier New', monospace; -fx-font-size: 12px;");

// Wrap the text area in a ScrollPane to ensure all content is accessible
ScrollPane scrollPane = new ScrollPane(area);
scrollPane.setFitToWidth(true);
scrollPane.setFitToHeight(true);
scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
```
- Lines 1590-1593: Creates another text area for displaying the report, makes it non-editable, sets its preferred height, and applies a monospaced font style.
- Lines 1596-1599: Creates a scroll pane containing the first text area, makes it fit to width and height, and sets the vertical scrollbar policy to appear as needed.

Lines 1601-1607:
```
// Main content
VBox content = new VBox(15);
content.setPadding(new Insets(15));

Label sectionLabel = new Label("Sales Summary Report:");
sectionLabel.getStyleClass().add("section-header");
```
- Lines 1602-1603: Creates a vertical box for the main content with 15 pixels spacing and adds padding.
- Lines 1605-1606: Creates a section label for the sales summary report and applies a CSS style.

Lines 1608-1628:
```
// Add download dropdown button
HBox buttonBox = new HBox(10);
buttonBox.setAlignment(Pos.CENTER_RIGHT);

// Create a menu button (dropdown)
MenuButton downloadBtn = new MenuButton("Download Report");
downloadBtn.getStyleClass().add("action-button");

// Create menu items
MenuItem downloadTxtItem = new MenuItem("Download as TXT");
MenuItem downloadPdfItem = new MenuItem("Download as PDF");

// Apply direct styling to ensure visibility
downloadTxtItem.setStyle("-fx-text-fill: white;");
downloadPdfItem.setStyle("-fx-text-fill: white;");

// Add menu items to the button
downloadBtn.getItems().addAll(downloadTxtItem, downloadPdfItem);

buttonBox.getChildren().add(downloadBtn);
content.getChildren().addAll(sectionLabel, buttonBox, scrollPane);
```
- Lines 1609-1610: Creates a horizontal box for the download button with 10 pixels spacing and right-aligns its content.
- Lines 1613-1614: Creates a menu button (dropdown) for downloading the report and applies a CSS style.
- Lines 1617-1618: Creates menu items for downloading as TXT and PDF.
- Lines 1621-1622: Applies direct styling to the menu items to ensure they are visible.
- Line 1625: Adds the menu items to the menu button.
- Line 1627: Adds the menu button to the button box.
- Line 1628: Adds the section label, button box, and scroll pane to the content container.

Line 1630:
```
mainLayout.setCenter(content);
```
- Line 1630: Places the content in the center of the main layout.

Lines 1632-1636:
```
StringBuilder report = new StringBuilder();
report.append(String.format("%-5s %-25s %-22s %-22s %-15s %-20s\n",
        "ID", "Vehicle", "Customer", "Employee", "Amount", "Date"));
report.append("-------------------------------------------------------------------------------------------------------------------------------------------------\n");
```
- Line 1632: Creates a StringBuilder for building the report content.
- Lines 1633-1634: Appends a formatted header row with column titles.
- Line 1635: Appends a separator line.

Lines 1637-1693:
```
try {
    // Get all sales
    List<Sale> sales = saleDAO.getAll();

    if (sales.isEmpty()) {
        area.setText("No sales data available for the report.");
    } else {
        int count = 1;
        double totalSales = 0;

        for (Sale sale : sales) {
            // Get related car details
            Car car = carDAO.getById(sale.getCarId());

            // Get related customer details
            Customer customer = customerDAO.getById(sale.getCustomerId());

            // Get related employee details
            Employee employee = employeeDAO.getById(sale.getEmployeeId());

            if (car != null && customer != null && employee != null) {
                String vehicle = car.getModel() + " " + car.getVariant();
                String customerName = customer.getName();
                String employeeName = employee.getName();
                double amount = sale.getAmount();
                totalSales += amount;

                report.append(String.format("%d. %-27s %-22s %-22s Rs.%-12.2f %-20s\n",
                    count++, 
                    vehicle, 
                    customerName, 
                    employeeName, 
                    amount, 
                    sale.getDate()));
            } else {
                // If any related entity is not found, show IDs instead
                report.append(String.format("%d. %-22s %-25s %-25s Rs.%-12.2f %-20s\n",
                    count++,
                    "Car ID: " + sale.getCarId(), 
                    "Customer ID: " + sale.getCustomerId(), 
                    "Employee ID: " + sale.getEmployeeId(), 
                    sale.getAmount(), 
                    sale.getDate()));
                totalSales += sale.getAmount();
            }
        }

        if (count == 1) {
            // No complete sales records found
            area.setText("No complete sales records found with valid references.");
        } else {
            // Add total sales at the end
            report.append("\n-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            report.append(String.format("Total Sales: Rs.%.2f", totalSales));
            area.setText(report.toString());
        }
    }
} catch (Exception e) {
    e.printStackTrace();
    area.setText("Failed to load report: " + e.getMessage());
}
```
- Line 1639: Gets all sales from the database.
- Lines 1641-1642: If there are no sales, displays a message.
- Lines 1644-1645: Initializes a counter and a variable to track the total sales amount.
- Lines 1647-1682: For each sale:
  - Lines 1649-1655: Gets the related car, customer, and employee details.
  - Lines 1657-1671: If all related entities are found, formats a row with their details and adds it to the report.
  - Lines 1672-1681: If any related entity is not found, formats a row with the IDs instead and adds it to the report.
- Lines 1684-1692: If no complete sales records were found, displays a message; otherwise, adds the total sales amount to the report and sets the text area content.
- Lines 1694-1697: Catches any exceptions that occur, prints the stack trace, and displays an error message.

Lines 1699-1706:
```
// Set up download menu items actions
downloadTxtItem.setOnAction(e -> {
    saveSalesReportAsTxt(report.toString());
});

downloadPdfItem.setOnAction(e -> {
    saveSalesReportAsPdf(report.toString());
});
```
- Lines 1700-1702: Sets an action to be performed when the "Download as TXT" menu item is clicked, which calls the saveSalesReportAsTxt method.
- Lines 1704-1706: Sets an action to be performed when the "Download as PDF" menu item is clicked, which calls the saveSalesReportAsPdf method.

Lines 1708-1711:
```
// Set the scene
scene.setRoot(mainLayout);
primaryStage.setScene(scene);
primaryStage.setTitle("Sales Report - Platinum Auto Japan");
```
- Lines 1709-1711: Sets the main layout as the root of the scene, sets the scene on the primary stage, and sets the title of the window.

### showCommissionWindow Method (Lines 1720-1839)

Lines 1720-1724:
```
private void showCommissionWindow(Stage primaryStage) {
    // Load CSS
    Scene scene = new Scene(new VBox(), 800, 900);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 1720: Declares a private method to show the commission viewer window.
- Line 1722: Creates a new Scene with a VBox layout and dimensions of 800x900 pixels.
- Lines 1723-1724: Loads and adds the CSS stylesheet to the scene.

Lines 1726-1728:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");
```
- Lines 1727-1728: Creates a BorderPane layout as the main container and adds a CSS class for styling.

Lines 1730-1752:
```
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
```
- Lines 1731-1733: Creates a horizontal box for the header with 15 pixels spacing, left-aligns its content, and adds padding.
- Lines 1735-1737: Creates a back button with an arrow symbol, applies a CSS style, and sets its action to show the main dashboard when clicked.
- Lines 1740-1746: Loads the commission icon image, creates an ImageView to display it, sets its size to 40x40 pixels, and adds it to the header.
- Lines 1748-1749: Creates a title label for the window and applies a CSS style.
- Lines 1751-1752: Adds the back button and title to the header and places the header at the top of the main layout.

Lines 1754-1761:
```
// Main content
VBox content = new VBox(15);
content.setPadding(new Insets(15));
content.setAlignment(Pos.CENTER);

Label sectionLabel = new Label("Select an Employee to View Commission:");
sectionLabel.getStyleClass().add("section-header");
content.getChildren().add(sectionLabel);
```
- Lines 1755-1757: Creates a vertical box for the main content with 15 pixels spacing, adds padding, and centers its content.
- Lines 1759-1761: Creates a section label with instructions, applies a CSS style, and adds it to the content container.

Lines 1763-1769:
```
// Grid for employee buttons
GridPane employeeGrid = new GridPane();
employeeGrid.setStyle("-fx-background-color: #212235;");
employeeGrid.setAlignment(Pos.CENTER);
employeeGrid.setHgap(15);
employeeGrid.setVgap(15);
employeeGrid.setPadding(new Insets(20));
```
- Lines 1764-1769: Creates a grid pane for the employee buttons, sets its background color, centers its content, sets horizontal and vertical gaps between cells, and adds padding.

Lines 1771-1831:
```
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
```
- Line 1773: Gets all employees from the database.
- Lines 1775-1778: If there are no employees, displays a message.
- Lines 1780-1783: Initializes variables for tracking the row, column, and maximum number of columns in the grid.
- Lines 1785-1816: For each employee:
  - Lines 1786-1787: Gets the employee ID and name.
  - Lines 1790-1792: Creates a button and sets its size.
  - Lines 1795-1803: Creates a vertical box for the button content, adds labels for the employee name and ID, and sets the button's graphic.
  - Line 1806: Sets an action to show commission details when the button is clicked.
  - Line 1808: Adds the button to the grid at the current row and column.
  - Lines 1811-1815: Updates the row and column for the next button.
- Lines 1819-1824: Creates a scroll pane for the employee grid, sets its properties, and adds it to the content container.
- Lines 1826-1830: Catches any exceptions that occur, prints the stack trace, and displays an error message.

Lines 1833-1838:
```
mainLayout.setCenter(content);

// Set the scene
scene.setRoot(mainLayout);
primaryStage.setScene(scene);
primaryStage.setTitle("Commission Viewer - Platinum Auto Japan");
```
- Line 1833: Places the content in the center of the main layout.
- Lines 1836-1838: Sets the main layout as the root of the scene, sets the scene on the primary stage, and sets the title of the window.

### showCommissionDetails Method (Lines 1849-1965)

Lines 1849-1853:
```
private void showCommissionDetails(Stage primaryStage, String empId, String empName) {
    // Load CSS
    Scene scene = new Scene(new VBox(), 800, 900);
    String css = getClass().getResource("/com/example/projectfxv5/css/styles.css").toExternalForm();
    scene.getStylesheets().add(css);
```
- Line 1849: Declares a private method to show commission details for a specific employee, taking the primary stage, employee ID, and employee name as parameters.
- Line 1851: Creates a new Scene with a VBox layout and dimensions of 800x900 pixels.
- Lines 1852-1853: Loads and adds the CSS stylesheet to the scene.

Lines 1855-1857:
```
// Main container
BorderPane mainLayout = new BorderPane();
mainLayout.getStyleClass().add("dashboard-container");
```
- Lines 1856-1857: Creates a BorderPane layout as the main container and adds a CSS class for styling.

Lines 1859-1881:
```
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
```
- Lines 1860-1862: Creates a horizontal box for the header with 15 pixels spacing, left-aligns its content, and adds padding.
- Lines 1864-1866: Creates a back button with an arrow symbol, applies a CSS style, and sets its action to show the commission window when clicked.
- Lines 1869-1875: Loads the commission icon image, creates an ImageView to display it, sets its size to 40x40 pixels, and adds it to the header.
- Lines 1877-1878: Creates a title label for the window and applies a CSS style.
- Lines 1880-1881: Adds the back button and title to the header and places the header at the top of the main layout.

Lines 1883-1893:
```
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
```
- Lines 1884-1887: Creates a text area for displaying commission details, makes it non-editable, sets its preferred height, and applies a monospaced font style.
- Lines 1890-1893: Creates a scroll pane containing the text area, makes it fit to width and height, and sets the vertical scrollbar policy to appear as needed.

Lines 1895-1912:
```
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
```
- Lines 1896-1897: Creates a vertical box for the main content with 15 pixels spacing and adds padding.
- Lines 1900-1901: Creates a horizontal box for the employee information with 10 pixels spacing and left-aligns its content.
- Lines 1903-1904: Creates a label for the employee information and applies a CSS style.
- Lines 1906-1907: Creates a label with the employee name and applies a style to make it bold, larger, and blue.
- Line 1909: Adds the employee label and name to the employee information box.
- Lines 1911-1912: Adds the employee information box and scroll pane to the content container and places the content in the center of the main layout.

Lines 1914-1916:
```
StringBuilder report = new StringBuilder();
report.append(String.format("%-30s %-15s %-15s\n", "Car", "Price", "Commission"));
report.append("------------------------------------------------------------\n");
```
- Line 1914: Creates a StringBuilder for building the report content.
- Line 1915: Appends a formatted header row with column titles.
- Line 1916: Appends a separator line.

Lines 1918-1919:
```
double totalCommission = 0;
double totalSales = 0;
```
- Lines 1918-1919: Initializes variables to track the total commission and total sales.

Lines 1921-1959:
```
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
```
- Line 1923: Gets all sales for the specified employee from the database.
- Lines 1925-1926: If there are no sales, displays a message.
- Line 1929: Sets the commission rate to 10%.
- Line 1932: Adds the commission rate information to the report.
- Lines 1935-1948: For each sale:
  - Line 1937: Gets the car details from the database.
  - Lines 1939-1947: If the car is found, calculates the commission, adds it to the total, and adds a row to the report.
- Lines 1951-1954: Adds the total sales and total commission to the report and sets the text area content.
- Lines 1956-1958: Catches any exceptions that occur, prints the stack trace, and displays an error message.

Lines 1961-1964:
```
// Set the scene
scene.setRoot(mainLayout);
primaryStage.setScene(scene);
primaryStage.setTitle("Commission Details - " + empName + " - Platinum Auto Japan");
```
- Lines 1962-1964: Sets the main layout as the root of the scene, sets the scene on the primary stage, and sets the title of the window with the employee name.

### main Method (Lines 1972-1974)

Lines 1972-1974:
```
public static void main(String[] args) {
    launch(args);
}
```
- Line 1972: Declares the main method, which is the entry point for the application.
- Line 1973: Calls the launch method from the Application class, which initializes the JavaFX toolkit, constructs an instance of the Projectv5 class, and calls its start method.

This completes the line-by-line explanation of the Projectv5.java file, which is the main application class for the Automotive Inventory System.
