


import DAO.CustomerDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if customerId is present in the session
        Integer customerId = (Integer) session.getAttribute("customerId");
        System.out.println("Customer ID from session: " + customerId);

        if (customerId == null) {
            response.sendRedirect("index.html");
            return;
        }

        // Retrieve form data
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        // Check if required parameters are present to determine if it's a form submission
        if (fullName == null || email == null || password == null || phone == null) {
            // Just forward to the JSP without setting any success or error messages
            request.getRequestDispatcher("customerEditProfile.jsp").forward(request, response);
            return;
        }

        // Create Customer object
        Customer customer = new Customer(customerId, fullName, email, password, phone);

        // Update in database
        CustomerDAO customerDAO = new CustomerDAO();
        boolean isUpdated = customerDAO.updateCustomer(customer);

        if (isUpdated) {
            // Update session data
            session.setAttribute("loggedInCustomer", customer);
            request.setAttribute("successMessage", "Profile updated successfully!");
        } else {
            request.setAttribute("errorMessage", "Unable to update profile. Please try again.");
        }

        // Forward back to editProfile.jsp
        request.getRequestDispatcher("customerEditProfile.jsp").forward(request, response);
    }
}


