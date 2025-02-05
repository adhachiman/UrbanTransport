import DAO.AdminDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Mapping for the Servlet
@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        boolean success = AdminDAO.registerAdmin(fullName, email, password, phone);

        if (success) {
            response.sendRedirect("adminLogin.jsp"); // Redirect to login page
        } else {
            response.getWriter().println("<h3>Registration failed! Try again.</h3>");
        }
    }
}
