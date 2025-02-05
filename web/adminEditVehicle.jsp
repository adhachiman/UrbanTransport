<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Transportation Data</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7fc;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 90%;
            max-width: 800px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px 30px;
        }

        h1 {
            color: #007bff;
            text-align: center;
            margin-bottom: 20px;
        }
        
        h2 {
            color: #00008B;
            text-align: center;
            margin-bottom: 20px;
        }

        hr {
            border: 1px solid #007bff;
            margin: 30px 0;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        table th, table td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #007bff;
            color: white;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .action-buttons form input[type="submit"] {
            padding: 6px 12px;
            font-size: 14px;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-align: center;
            color: #007bff;
            font-weight: bold;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .container {
                padding: 15px;
            }

            input[type="text"], input[type="number"] {
                font-size: 14px;
                padding: 8px;
            }

            input[type="submit"] {
                font-size: 14px;
                padding: 8px;
            }

            table th, table td {
                font-size: 12px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Transportation Data</h1>

        <!-- Form to Add a New Vehicle -->
        <h2>Add New Vehicle</h2>
        <form action="VehicleServlet" method="post">
            <label for="vehicleType">Vehicle Type:</label>
            <input type="text" id="vehicleType" name="vehicleType" placeholder="Enter vehicle type" required />

            <label for="vehicleCharge">Vehicle Charge:</label>
            <input type="number" id="vehicleCharge" name="vehicleCharge" placeholder="Enter charge" step="0.01" required />

            <input type="hidden" name="action" value="add" />
            <input type="submit" value="Add Vehicle" />
        </form>

        <hr />

        <!-- Table to Display and Edit/Delete Existing Vehicles -->
        <h2>Existing Vehicles</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Charge</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vehicle" items="${vehicles}">
                    <tr>
                        <td>${vehicle.id}</td>
                        <td>${vehicle.type}</td>
                        <td>${vehicle.charge}</td>
                        <td class="action-buttons">
                            <form action="EditSingleVehicleServlet" method="get">
                                <input type="hidden" name="vehicleId" value="${vehicle.id}" />
                                <input type="submit" value="Edit" />
                            </form>
                            <form action="VehicleServlet" method="post">
                                <input type="hidden" name="vehicleId" value="${vehicle.id}" />
                                <input type="hidden" name="action" value="delete" />
                                <input type="submit" value="Delete" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="AdminDashboardServlet" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
