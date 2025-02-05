import DAO.CustomerDAO;
import DAO.VehicleDAO;
import model.Customer;
import model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListAvailableTransportServlet")
public class ListAvailableTransportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all vehicles from the DAO
        List<Vehicle> vehicles = VehicleDAO.getAllVehicles();

        // Set the list of vehicles as a request attribute
        request.setAttribute("vehicles", vehicles);

        // Get the customer ID from the session
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId"); // Retrieve customer ID from session

        // If the customer is not logged in, redirect to login page
        if (customerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch customer information from DAO using the customerId
        Customer customer = CustomerDAO.getCustomerById(customerId);

        // If customer is not found, redirect to login page
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Set the customer object as a request attribute
        request.setAttribute("customer", customer);

        // Forward the request to the JSP page
        request.getRequestDispatcher("customerListAvailableTransport.jsp").forward(request, response);
    }
}
