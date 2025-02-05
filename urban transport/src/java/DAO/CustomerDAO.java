package DAO;

import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static final String DB_URL = "jdbc:derby://localhost:1527/vehicle_booking";
    private static final String DB_USER = "app";  // Default user for JavaDB
    private static final String DB_PASSWORD = "app";  // Default password

    // Method to get a connection (to avoid repeating the code every time)
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Check if an email is already registered
    public static boolean isEmailRegistered(String email) {
        String query = "SELECT 1 FROM customer WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true; // Email exists
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking email registration: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // Email does not exist
    }

    // Validate Customer credentials and return Customer object
    public static Customer validateCustomer(String email, String password) {
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE email = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error validating customer: " + e.getMessage());
            e.printStackTrace();
        }
        return customer;
    }

    // Register a new Customer
    public static boolean registerCustomer(String fullName, String email, String password, String phone) {
        String query = "INSERT INTO customer (fullname, email, password, phone_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error registering customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve customer details by ID
    public static Customer getCustomerById(int customerId) {
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE customer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customer by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return customer;
    }

    // Retrieve all customers
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all customers: " + e.getMessage());
            e.printStackTrace();
        }
        return customers;
    }

    // Update customer profile
    public static boolean updateCustomer(Customer customer) {
        String query = "UPDATE customer SET fullname = ?, email = ?, password = ?, phone_number = ? WHERE customer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.setString(4, customer.getPhone());
            ps.setInt(5, customer.getCustomerId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Error updating customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Delete customer by ID
    public static boolean deleteCustomer(int customerId) {
        String query = "DELETE FROM customer WHERE customer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, customerId);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
