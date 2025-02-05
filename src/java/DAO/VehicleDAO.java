package DAO;



import model.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private static final String DB_URL = "jdbc:derby://localhost:1527/vehicle_booking";
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "app";

    // Fetch all vehicles from the database
    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM vehicle";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                vehicles.add(new Vehicle(
                    rs.getInt("vehicle_id"), 
                    rs.getString("vehicle_type"), 
                    rs.getDouble("vehicle_charge")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Add a new vehicle to the database
    public static void addVehicle(Vehicle vehicle) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO vehicle (vehicle_type, vehicle_charge) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, vehicle.getType());
            ps.setDouble(2, vehicle.getCharge());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a specific vehicle by its ID
    public static Vehicle getVehicleById(int id) {
        Vehicle vehicle = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM vehicle WHERE vehicle_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                vehicle = new Vehicle(
                    rs.getInt("vehicle_id"), 
                    rs.getString("vehicle_type"), 
                    rs.getDouble("vehicle_charge")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    // Update an existing vehicle's details
    public static void updateVehicle(Vehicle vehicle) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE vehicle SET vehicle_type = ?, vehicle_charge = ? WHERE vehicle_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, vehicle.getType());
            ps.setDouble(2, vehicle.getCharge());
            ps.setInt(3, vehicle.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a vehicle by its ID
    public static void deleteVehicle(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM vehicle WHERE vehicle_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
