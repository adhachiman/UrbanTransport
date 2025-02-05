import DAO.CustomerDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Mapping for the Servlet
@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        // Check if the email is already registered
        if (CustomerDAO.isEmailRegistered(email)) {
            // Send a JavaScript alert as the response
            response.setContentType("text/html");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('The email is already registered. Please use a different email.');");
            response.getWriter().println("history.back();"); // Return to the previous page
            response.getWriter().println("</script>");
        } else {
            // Proceed with registration
            boolean success = CustomerDAO.registerCustomer(fullName, email, password, phone);

            if (success) {
                response.getWriter().println("<script>");
                response.getWriter().println("alert('Registration successful!');");
                response.getWriter().println("window.location = 'customerLogin.jsp';"); // Redirect to login page
                response.getWriter().println("</script>");
            } else {
                response.getWriter().println("<script>");
                response.getWriter().println("alert('Registration failed! Try again.');");
                response.getWriter().println("history.back();"); // Return to the previous page
                response.getWriter().println("</script>");
            }
        }
    }
}
