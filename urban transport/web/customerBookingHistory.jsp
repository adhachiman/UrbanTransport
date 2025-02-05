<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking History</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fb;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            max-width: 900px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 2.5em;
            color: #2c3e50;
            text-align: center;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }

        table th, table td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #3498db;
            color: white;
            font-size: 1.1em;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tr:hover {
            background-color: #ecf0f1;
        }

        .no-bookings {
            font-size: 1.2em;
            color: #e74c3c;
            text-align: center;
            margin: 20px 0;
        }

        .back-link {
            display: block;
            text-align: center;
            font-size: 1.2em;
            color: #3498db;
            text-decoration: none;
            margin-top: 30px;
            padding: 10px;
            border: 2px solid #3498db;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        .back-link:hover {
            background-color: #3498db;
            color: white;
        }

    </style>
</head>
<body>

    <div class="container">
        <h1>Booking History</h1>

        <c:choose>
            <c:when test="${empty bookings}">
                <p class="no-bookings">No bookings found.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Vehicle Type</th>
                            <th>Pickup Time</th>
                            <th>Pickup Location</th>
                            <th>Dropoff Location</th>
                            <th>Total Charge</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="booking" items="${bookings}">
                            <tr>
                                <td>${booking.bookingId}</td>
                                <td>${booking.vehicleType}</td>
                                <td>${booking.pickupTime}</td>
                                <td>${booking.pickupLocation}</td>
                                <td>${booking.dropoffLocation}</td>
                                <td>RM ${booking.totalCharge}</td>
                                <td>${booking.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <a href="ListAvailableTransportServlet" class="back-link">Back to Transportation List</a>
    </div>

</body>
</html>
