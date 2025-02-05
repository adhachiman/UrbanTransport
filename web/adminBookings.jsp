<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Bookings</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f4f9;
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-bottom: 30px;
        }

        h1 {
            margin: 20px 0;
            text-align: center;
            font-size: 2.5rem;
            color: #007bff;
        }

        table {
            width: 90%;
            max-width: 1200px;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #ffffff;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        table thead {
            background-color: #007bff;
            color: white;
        }

        table th,
        table td {
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        table tbody tr:hover {
            background-color: #f1f8ff;
        }

        select {
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        button {
            padding: 8px 16px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #218838;
        }

        form {
            display: inline-block;
        }

        .back-link {
            text-decoration: none;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border-radius: 4px;
            font-size: 16px;
            font-weight: bold;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease;
        }

        .back-link:hover {
            background-color: #0056b3;
        }

        @media (max-width: 768px) {
            table {
                font-size: 14px;
            }

            button {
                font-size: 12px;
                padding: 6px 12px;
            }

            select {
                font-size: 12px;
            }
        }
    </style>
</head>
<body>
    <h1>Manage Bookings</h1>

    <table>
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>Customer ID</th>
                <th>Vehicle ID</th>
                <th>Pickup Time</th>
                <th>Pickup Location</th>
                <th>Dropoff Location</th>
                <th>Distance</th>
                <th>Total Charge</th>
                <th>Status</th>
                <th>Vehicle Type</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${bookings}">
                <tr>
                    <td>${booking.bookingId}</td>
                    <td>${booking.customerId}</td>
                    <td>${booking.vehicleId}</td>
                    <td>${booking.pickupTime}</td>
                    <td>${booking.pickupLocation}</td>
                    <td>${booking.dropoffLocation}</td>
                    <td>${booking.distance}</td>
                    <td>${booking.totalCharge}</td>
                    <td>${booking.status}</td>
                    <td>${booking.vehicleType}</td>
                    <td>
                        <form action="AdminBookingServlet" method="post">
                            <input type="hidden" name="booking_id" value="${booking.bookingId}" />
                            <select name="status">
                                <option value="Pending" ${booking.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                <option value="Confirmed" ${booking.status == 'Confirmed' ? 'selected' : ''}>Confirmed</option>
                                <option value="Cancelled" ${booking.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                            </select>
                            <button type="submit">Update</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="AdminDashboardServlet" class="back-link">Back to Dashboard</a>
</body>
</html>
