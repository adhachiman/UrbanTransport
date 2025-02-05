import DAO.VehicleDAO;
import model.Vehicle;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/VehicleServlet")
public class VehicleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all vehicles from the database
        List<Vehicle> vehicles = VehicleDAO.getAllVehicles();
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("adminEditVehicle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Handle adding a new vehicle
            try {
                String type = request.getParameter("vehicleType");
                double charge = Double.parseDouble(request.getParameter("vehicleCharge"));
                Vehicle newVehicle = new Vehicle(0, type, charge);
                VehicleDAO.addVehicle(newVehicle);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "Invalid input for charge.");
            }
        } else if ("delete".equals(action)) {
            // Handle deleting a vehicle
            try {
                int id = Integer.parseInt(request.getParameter("vehicleId"));
                VehicleDAO.deleteVehicle(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "Invalid vehicle ID.");
            }
        }

        // Redirect back to the edit vehicle page
        response.sendRedirect("VehicleServlet");
    }
}
