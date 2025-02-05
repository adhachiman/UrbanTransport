

import DAO.VehicleDAO;
import model.Vehicle;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditSingleVehicleServlet")
public class EditSingleVehicleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load the vehicle data for editing
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        Vehicle vehicle = VehicleDAO.getVehicleById(vehicleId);

        if (vehicle != null) {
            request.setAttribute("vehicle", vehicle);
            request.getRequestDispatcher("adminEditSingleVehicle.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update the vehicle data
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String vehicleType = request.getParameter("vehicleType");
        double vehicleCharge = Double.parseDouble(request.getParameter("vehicleCharge"));

        Vehicle updatedVehicle = new Vehicle(vehicleId, vehicleType, vehicleCharge);
        VehicleDAO.updateVehicle(updatedVehicle);

        // Redirect back to the main edit vehicle page
        response.sendRedirect("VehicleServlet");
    }
}
