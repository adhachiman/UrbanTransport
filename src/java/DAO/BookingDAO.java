package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Booking;

public class BookingDAO {
    private static final String DB_URL = "jdbc:derby://localhost:1527/vehicle_booking";
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "app";

    // Add Booking and Return Booking ID
    public static int addBooking(Booking booking) {
        int bookingId = -1;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO booking (customer_id, vehicle_id, pickup_time, pickup_location, dropoff_location, distance, total_charge, status) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, booking.getCustomerId());
            ps.setInt(2, booking.getVehicleId());
            ps.setTimestamp(3, booking.getPickupTime());
            ps.setString(4, booking.getPickupLocation());
            ps.setString(5, booking.getDropoffLocation());
            ps.setDouble(6, booking.getDistance());
            ps.setDouble(7, booking.getTotalCharge());
            ps.setString(8, "Pending");

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    bookingId = rs.getInt(1);
                }
            } else {
                System.err.println("Booking insertion failed. No rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingId;
    }

    // Retrieve Booking by ID
    public static Booking getBookingById(int bookingId) {
        Booking booking = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT b.*, v.vehicle_type FROM booking b " +
                           "JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
                           "WHERE b.booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("vehicle_id"),
                    rs.getTimestamp("pickup_time"),
                    rs.getString("pickup_location"),
                    rs.getString("dropoff_location"),
                    rs.getDouble("distance"),
                    rs.getDouble("total_charge"),
                    rs.getString("status"),
                    rs.getString("vehicle_type") // Retrieve vehicle type
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }

    // Fetch all bookings
    public static List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.*, v.vehicle_type FROM booking b " +
                       "JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
                       "ORDER BY b.pickup_time DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("vehicle_id"),
                    rs.getTimestamp("pickup_time"),
                    rs.getString("pickup_location"),
                    rs.getString("dropoff_location"),
                    rs.getDouble("distance"),
                    rs.getDouble("total_charge"),
                    rs.getString("status"),
                    rs.getString("vehicle_type") // Retrieve vehicle type
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Update the booking status
    public static boolean updateBookingStatus(int bookingId, String status) {
        boolean updated = false;
        String query = "UPDATE booking SET status = ? WHERE booking_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setInt(2, bookingId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    // Delete Booking by ID
    public static boolean deleteBooking(int bookingId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM booking WHERE booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if a row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch all bookings for a specific customer
    public static List<Booking> getBookingsByCustomerId(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.*, v.vehicle_type FROM booking b " +
                       "JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
                       "WHERE b.customer_id = ? ORDER BY b.pickup_time DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("vehicle_id"),
                        rs.getTimestamp("pickup_time"),
                        rs.getString("pickup_location"),
                        rs.getString("dropoff_location"),
                        rs.getDouble("distance"),
                        rs.getDouble("total_charge"),
                        rs.getString("status"),
                        rs.getString("vehicle_type") // Retrieve vehicle type
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
