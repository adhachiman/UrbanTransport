import DAO.BookingDAO;
import DAO.VehicleDAO;
import model.Booking;
import model.Vehicle;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/formSubmission")
public class FormSubmissionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            Vehicle vehicle = VehicleDAO.getVehicleById(vehicleId);

            if (vehicle == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found");
                return;
            }

            request.setAttribute("vehicle", vehicle);
            request.getRequestDispatcher("customerFormSubmission.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid vehicle ID");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching vehicle details");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Parse request parameters
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            String pickupTimeStr = request.getParameter("pickupTime").replace("T", " ") + ":00";
            String pickupLocation = request.getParameter("pickupLocation");
            String dropoffLocation = request.getParameter("dropoffLocation");
            double distance = Double.parseDouble(request.getParameter("distance")); // Retrieve distance from the request

            // Fetch vehicle details
            Vehicle vehicle = VehicleDAO.getVehicleById(vehicleId);
            if (vehicle == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found");
                return;
            }

            // Retrieve customer ID from session
            HttpSession session = request.getSession();
            Integer customerId = (Integer) session.getAttribute("customerId"); // Get customer ID from session

            if (customerId == null) {
                response.sendRedirect("index.html");
                return;
            }

            // Convert pickup time
            Timestamp pickupTime = Timestamp.valueOf(pickupTimeStr);

            // Calculate total charge based on distance and vehicle charge
            double totalCharge = distance * vehicle.getCharge(); // Assuming `getChargePerKm` exists in `Vehicle`

            // Create a new booking object
            Booking booking = new Booking(customerId, vehicleId, pickupTime, pickupLocation, dropoffLocation, distance, totalCharge);

            // Save the booking to the database
            int bookingId = BookingDAO.addBooking(booking);

            // Redirect to the receipt servlet if booking is successful
            if (bookingId > 0) {
                response.sendRedirect("ReceiptServlet?bookingId=" + bookingId);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create booking");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the booking");
        }
    }
}
