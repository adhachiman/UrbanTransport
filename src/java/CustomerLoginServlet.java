import DAO.CustomerDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate customer and retrieve customer object
        Customer customer = CustomerDAO.validateCustomer(email, password);
        
        if (customer != null) {
            // If customer exists, store customer details in session
            HttpSession session = request.getSession();
            session.setAttribute("loggedInCustomer", customer);
            session.setAttribute("customerEmail", email);
            session.setAttribute("customerId", customer.getCustomerId());  // Store customer ID in session
           


            // Redirect to transport list page
            response.sendRedirect("ListAvailableTransportServlet");
        } else {
            // If invalid credentials, show error message
            response.getWriter().println("<h3>Invalid credentials! Try again.</h3>");
        }
    }
}
