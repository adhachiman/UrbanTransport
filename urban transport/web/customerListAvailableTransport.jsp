<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Available Transport</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #74ebd5, #9face6);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 90%;
            max-width: 900px;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            text-align: center;
            padding: 30px;
        }

        h1 {
            font-size: 2.5rem;
            color: #2c3e50;
            margin-bottom: 20px;
        }
        
        h2 {
            font-size: 1.5rem;
            color: #00008B;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.2rem;
            color: #7f8c8d;
            margin: 10px 0;
        }

        .button-container {
            margin: 20px 0;
            display: flex;
            justify-content: center;
            gap: 15px;
        }

        button {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 1rem;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #2980b9;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        table th, table td {
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #3498db;
            color: white;
            font-size: 1rem;
        }

        table td {
            background-color: #f9f9f9;
            font-size: 1rem;
        }

        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        table tr:hover {
            background-color: #ecf0f1;
        }

        .btn-book {
            background-color: #2ecc71;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-book:hover {
            background-color: #27ae60;
        }

        .no-data {
            color: #e74c3c;
            font-size: 1.2rem;
            margin: 20px 0;
        }

        .history-link {
            margin: 20px 0;
            display: inline-block;
            font-size: 1.1rem;
            color: #3498db;
            text-decoration: none;
            padding: 10px 20px;
            border: 2px solid #3498db;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        .history-link:hover {
            background-color: #3498db;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Transport Booking</h1>

        <!-- Display Customer Name -->
        <p>Welcome, <strong>${customer.fullName}</strong>!</p>

        <!-- Display Customer ID -->
        <p>Customer ID: <strong>${customerId}</strong></p>

        <!-- Buttons for Logout and Profile Edit -->
        <div class="button-container">
            <form action="LogoutServlet" method="get" style="display: inline;">
                <button type="submit">Logout</button>
            </form>
            <form action="EditProfileServlet" method="post" style="display: inline;">
                <button type="submit">Edit Profile</button>
            </form>
        </div>
        
        <h2>Choose and Book your transportation here</h2>

        <!-- If there are vehicles, display them in a table -->
        <c:if test="${not empty vehicles}">
            <table>
                <thead>
                    <tr>
                        <th>Vehicle ID</th>
                        <th>Vehicle Type</th>
                        <th>Charge Per Trip</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vehicle" items="${vehicles}">
                        <tr>
                            <td>${vehicle.id}</td>
                            <td>${vehicle.type}</td>
                            <td>${vehicle.charge}</td>
                            <td>
                                <form action="FormSubmissionServlet" method="GET">
                                    <input type="hidden" name="vehicleId" value="${vehicle.id}">
                                    <button type="submit" class="btn-book">Book Now</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <!-- If no vehicles, show a "No vehicles available" message -->
        <c:if test="${empty vehicles}">
            <p class="no-data">No vehicles available at the moment. Please check back later.</p>
        </c:if>

        <!-- Link to booking history -->
        <a href="BookingHistoryServlet" class="history-link">My Booking History</a>
    </div>
</body>
</html>
