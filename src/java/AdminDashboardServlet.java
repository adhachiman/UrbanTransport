import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:derby://localhost:1527/vehicle_booking";
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "app";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalCustomers = 0;
        int totalAdmins = 0;
        int totalVehicles = 0;
        int totalBookings = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Get total customers
            String customersQuery = "SELECT COUNT(*) FROM customer";
            PreparedStatement ps1 = conn.prepareStatement(customersQuery);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                totalCustomers = rs1.getInt(1);
            }

            // Get total admins
            String adminsQuery = "SELECT COUNT(*) FROM admin";
            PreparedStatement ps2 = conn.prepareStatement(adminsQuery);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                totalAdmins = rs2.getInt(1);
            }

            // Get total vehicles
            String vehiclesQuery = "SELECT COUNT(*) FROM vehicle";
            PreparedStatement ps3 = conn.prepareStatement(vehiclesQuery);
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) {
                totalVehicles = rs3.getInt(1);
            }

            // Get total bookings
            String bookingsQuery = "SELECT COUNT(*) FROM booking";
            PreparedStatement ps4 = conn.prepareStatement(bookingsQuery);
            ResultSet rs4 = ps4.executeQuery();
            if (rs4.next()) {
                totalBookings = rs4.getInt(1);
            }

            // Check if the dashboard_summary entry exists
            String checkQuery = "SELECT COUNT(*) FROM dashboard_summary WHERE summary_id = 1";
            PreparedStatement psCheck = conn.prepareStatement(checkQuery);
            ResultSet rsCheck = psCheck.executeQuery();
            boolean exists = false;
            if (rsCheck.next()) {
                exists = rsCheck.getInt(1) > 0;
            }

            if (exists) {
                // Update the dashboard_summary table if exists
                String updateQuery = "UPDATE dashboard_summary SET total_customers = ?, total_admins = ?, total_vehicles = ?, total_bookings = ? WHERE summary_id = 1";
                PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
                psUpdate.setInt(1, totalCustomers);
                psUpdate.setInt(2, totalAdmins);
                psUpdate.setInt(3, totalVehicles);
                psUpdate.setInt(4, totalBookings);
                psUpdate.executeUpdate();
            } else {
                // Insert into the dashboard_summary table if no entry exists
                String insertQuery = "INSERT INTO dashboard_summary (summary_id, total_customers, total_admins, total_vehicles, total_bookings) VALUES (1, ?, ?, ?, ?)";
                PreparedStatement psInsert = conn.prepareStatement(insertQuery);
                psInsert.setInt(1, totalCustomers);
                psInsert.setInt(2, totalAdmins);
                psInsert.setInt(3, totalVehicles);
                psInsert.setInt(4, totalBookings);
                psInsert.executeUpdate();
            }

            // Set attributes for the JSP
            request.setAttribute("totalCustomers", totalCustomers);
            request.setAttribute("totalAdmins", totalAdmins);
            request.setAttribute("totalVehicles", totalVehicles);
            request.setAttribute("totalBookings", totalBookings);

            // Forward to the admin dashboard JSP
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching or updating dashboard data.");
        }
    }
}