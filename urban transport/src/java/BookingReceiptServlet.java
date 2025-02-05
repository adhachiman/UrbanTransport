import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookingReceiptServlet")
public class BookingReceiptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int transportId = Integer.parseInt(request.getParameter("transportId"));
        String pickupTime = request.getParameter("pickupTime");
        String routeStart = request.getParameter("routeStart");
        String routeEnd = request.getParameter("routeEnd");
        double distance = Double.parseDouble(request.getParameter("distance"));

        // Simulate fetching transport rate based on transportId
        double ratePerKm = 0.0;
        if (transportId == 1) ratePerKm = 2.5; // Car
        if (transportId == 2) ratePerKm = 3.0; // Van
        if (transportId == 3) ratePerKm = 4.0; // Bus

        // Calculate total charge
        double totalCharge = ratePerKm * distance;

        // Display receipt
        response.setContentType("text/html");
        response.getWriter().println("<h2>Booking Receipt</h2>");
        response.getWriter().println("<p>Pickup Time: " + pickupTime + "</p>");
        response.getWriter().println("<p>Route Start: " + routeStart + "</p>");
        response.getWriter().println("<p>Route End: " + routeEnd + "</p>");
        response.getWriter().println("<p>Distance: " + distance + " KM</p>");
        response.getWriter().println("<p>Total Charge: RM" + totalCharge + "</p>");
        response.getWriter().println("<a href='ListAvailableTransportServlet'>Back to Transport List</a>");
    }
}
