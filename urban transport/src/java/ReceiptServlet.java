import DAO.BookingDAO;
import DAO.VehicleDAO;
import model.Booking;
import model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ReceiptServlet")
public class ReceiptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the booking ID from the query string
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));

            // Retrieve the booking details from the database
            Booking booking = BookingDAO.getBookingById(bookingId);
            if (booking == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found");
                return;
            }

            // Retrieve the vehicle details for this booking
            Vehicle vehicle = VehicleDAO.getVehicleById(booking.getVehicleId());
            if (vehicle == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found");
                return;
            }

            // Pass the booking and vehicle details to the JSP
            request.setAttribute("booking", booking);
            request.setAttribute("vehicle", vehicle);

            // Forward the request to receipt.jsp
            request.getRequestDispatcher("customerReceipt.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid booking ID");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving the booking details");
        }
    }
}
