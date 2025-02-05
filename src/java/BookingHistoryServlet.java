import DAO.BookingDAO;
import model.Booking;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/BookingHistoryServlet")
public class BookingHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if customer is logged in
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            // Redirect to login page if not logged in
            response.sendRedirect("index.html");
            return;
        }

        try {
            // Fetch bookings for the logged-in customer
            List<Booking> bookings = BookingDAO.getBookingsByCustomerId(customerId);

            // Set the bookings in the request scope
            request.setAttribute("bookings", bookings);

            // Forward the request to bookingHistory.jsp
            request.getRequestDispatcher("customerBookingHistory.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving booking history");
        }
    }
}
