<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #4CAF50;
            color: white;
            padding: 15px 20px;
            text-align: center;
        }

        .dashboard-container {
            max-width: 900px;
            margin: 20px auto;
            padding: 20px;
            background: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
        }

        .stats {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 15px;
        }

        .stat-card {
            background: #f8f9fa;
            flex: 1 1 calc(48% - 10px);
            padding: 20px;
            text-align: center;
            border-radius: 8px;
            border: 1px solid #ddd;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .stat-card h3 {
            margin: 0;
            font-size: 18px;
            color: #333;
        }

        .stat-card p {
            font-size: 24px;
            margin: 10px 0 0;
            font-weight: bold;
            color: #4CAF50;
        }

        .actions {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }

        .actions a,
        .actions button {
            text-decoration: none;
            color: white;
            background-color: #4CAF50;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .actions a:hover,
        .actions button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Admin Dashboard</h1>
    </div>

    <div class="dashboard-container">
        <div class="stats">
            <!-- Statistics -->
            <div class="stat-card">
                <h3>Total Customers</h3>
                <p>${totalCustomers}</p>
            </div>
            <div class="stat-card">
                <h3>Total Admins</h3>
                <p>${totalAdmins}</p>
            </div>
            <div class="stat-card">
                <h3>Total Vehicles</h3>
                <p>${totalVehicles}</p>
            </div>
            <div class="stat-card">
                <h3>Total Bookings</h3>
                <p>${totalBookings}</p>
            </div>
        </div>

        <div class="actions">
            <!-- Actions -->
            <a href="VehicleServlet">Edit Vehicle Data</a>
            <a href="AdminBookingServlet">Edit Booking Approval</a>
            <a href="EditUsersServlet">Edit Users</a>
            <form action="LogoutServlet" method="get" style="margin: 0;">
                <button type="submit">Logout</button>
            </form>
        </div>
    </div>
</body>
</html>
