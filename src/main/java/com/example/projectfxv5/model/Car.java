package com.example.projectfxv5.model;

/**
 * Model class representing a Car entity.
 */
public class Car {
    private String id;
    private String model;
    private String variant;
    private double price;
    private double mileage;
    private String color;
    private String type;
    private double weight;
    private String numberPlate;
    private String chassisNumber;

    // Default constructor
    public Car() {
    }

    // Parameterized constructor
    public Car(String id, String model, String variant, double price, double mileage, 
               String color, String type, double weight, String numberPlate, String chassisNumber) {
        this.id = id;
        this.model = model;
        this.variant = variant;
        this.price = price;
        this.mileage = mileage;
        this.color = color;
        this.type = type;
        this.weight = weight;
        this.numberPlate = numberPlate;
        this.chassisNumber = chassisNumber;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    @Override
    public String toString() {
        // Format each field with appropriate width
        String formattedId = String.format("%-8s", id);
        String formattedModel = String.format("%-12s", model);
        String formattedVariant = String.format("%-15s", variant);
        String formattedPrice = String.format("%-12s", "£" + String.format("%,.2f", price));
        String formattedMileage = String.format("%-12s", String.format("%,.1f", mileage));
        String formattedColor = String.format("%-10s", color);
        String formattedType = String.format("%-10s", type);
        String formattedWeight = String.format("%-10s", String.format("%,.1f", weight));
        String formattedPlate = String.format("%-10s", numberPlate);
        String formattedChassis = String.format("%-10s", chassisNumber);

        // Create header row if this is the first car
        String header = String.format("%-8s %-12s %-15s %-12s %-12s %-10s %-10s %-10s %-10s %-10s",
                "ID", "Model", "Variant", "Price", "Mileage", "Color", "Type", "Weight", "Plate", "Chassis");

        // Create separator line
        String separator = "-".repeat(header.length());

        // Create data row
        String dataRow = String.format("%-8s %-12s %-15s %-12s %-12s %-10s %-10s %-10s %-10s %-10s",
                id, model, variant, "£" + String.format("%,.2f", price), String.format("%,.1f", mileage),
                color, type, String.format("%,.1f", weight), numberPlate, chassisNumber);

        return header + "\n" + separator + "\n" + dataRow;
    }
}
