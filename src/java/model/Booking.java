package model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int customerId;
    private int vehicleId;
    private Timestamp pickupTime;
    private String pickupLocation;
    private String dropoffLocation;
    private double distance;
    private double totalCharge;
    private String status;
    private String vehicleType;

    // Constructor for creating a new booking
    public Booking(int customerId, int vehicleId, Timestamp pickupTime, String pickupLocation, String dropoffLocation, double distance, double totalCharge) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.pickupTime = pickupTime;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distance = distance;
        this.totalCharge = totalCharge;
        this.status = "Pending";
    }

    // Constructor for retrieving an existing booking
    public Booking(int bookingId, int customerId, int vehicleId, Timestamp pickupTime, String pickupLocation, String dropoffLocation, double distance, double totalCharge, String status, String vehicleType) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.pickupTime = pickupTime;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distance = distance;
        this.totalCharge = totalCharge;
        this.status = status;
        this.vehicleType = vehicleType;
    }

    // Getters and Setters...
    // (Same as before)



    

    // Getters and setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Timestamp getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Timestamp pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleType() { // Getter for vehicle type
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) { // Setter for vehicle type
        this.vehicleType = vehicleType;
    }
}
