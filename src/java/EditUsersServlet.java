import DAO.AdminDAO;
import DAO.CustomerDAO;
import model.Customer;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EditUsersServlet")
public class EditUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch customer and admin data from the database
        List<Customer> customers = CustomerDAO.getAllCustomers();
        List<Admin> admins = AdminDAO.getAllAdmins();

        // Set data as request attributes
        request.setAttribute("customers", customers);
        request.setAttribute("admins", admins);

        // Forward to editUsers.jsp
        request.getRequestDispatcher("adminEditUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userType = request.getParameter("userType");
        int userId = Integer.parseInt(request.getParameter("userId"));

        if ("delete".equals(action)) {
            if ("customer".equals(userType)) {
                CustomerDAO.deleteCustomer(userId);
            } else if ("admin".equals(userType)) {
                AdminDAO.deleteAdmin(userId);
            }
        } else if ("edit".equals(action)) {
            // Redirect to the edit form
            response.sendRedirect("editForm.jsp?userId=" + userId + "&userType=" + userType);
            return;
        } else if ("update".equals(action)) {
            // Update logic
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");

            if ("customer".equals(userType)) {
                // Update customer details
                Customer customer = new Customer(userId, fullName, email, password, phone);
                boolean updated = CustomerDAO.updateCustomer(customer);

                if (!updated) {
                    request.setAttribute("error", "Unable to update customer details.");
                }
            } else if ("admin".equals(userType)) {
                // Update admin details
                Admin admin = new Admin(userId, fullName, email, password, phone);
                boolean updated = AdminDAO.updateAdmin(admin);

                if (!updated) {
                    request.setAttribute("error", "Unable to update admin details.");
                }
            }
        }

        // Refresh the page
        response.sendRedirect("EditUsersServlet");
    }
}
