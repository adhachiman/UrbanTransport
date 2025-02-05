import DAO.BookingDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Booking;


@WebServlet("/AdminBookingServlet")
public class AdminBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Booking> bookings = BookingDAO.getAllBookings();  // Use the DAO to fetch all bookings
        
        if (bookings != null) {
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("adminBookings.jsp").forward(request, response);
        } else {
            // Handle the case where no bookings were found
            request.setAttribute("message", "No bookings found.");
            request.getRequestDispatcher("adminBookings.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingId = request.getParameter("booking_id");
        String status = request.getParameter("status");

        try {
            if (BookingDAO.updateBookingStatus(Integer.parseInt(bookingId), status)) {
                response.sendRedirect("AdminBookingServlet");  // Redirect back to the GET method
            } else {
                // If update fails, show an error message
                request.setAttribute("message", "Failed to update the booking status.");
                request.getRequestDispatcher("adminBookings.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
