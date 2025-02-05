<%@ page import="model.Booking" %>
<%@ page import="model.Vehicle" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    // Retrieve the booking and vehicle objects passed as attributes from ReceiptServlet
    Booking booking = (Booking) request.getAttribute("booking");
    Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");

    if (booking == null || vehicle == null) {
        out.println("<h2>Booking or Vehicle details not found!</h2>");
        return;
    }

    // Format the pickup time
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedPickupTime = sdf.format(booking.getPickupTime());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Receipt</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
        }
        .receipt-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .receipt-header {
            text-align: center;
            margin-bottom: 20px;
        }
        .receipt-details {
            margin-bottom: 20px;
        }
        .btn-container {
            display: flex;
            justify-content: space-between;
        }
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .confirm-btn {
            background-color: #28a745;
            color: white;
        }
        .cancel-btn {
            background-color: #dc3545;
            color: white;
        }
    </style>
</head>
<body>
    <div class="receipt-container">
        <div class="receipt-header">
            <h1>Booking Receipt</h1>
        </div>

        <div class="receipt-details">
            <h3>Booking Details</h3>
            <p><strong>Booking ID:</strong> <%= booking.getBookingId() %></p>
            <p><strong>Customer ID:</strong> <%= booking.getCustomerId() %></p>
            <p><strong>Vehicle Name:</strong> <%= vehicle.getId() %></p>
            <p><strong>Pickup Time:</strong> <%= formattedPickupTime %></p>
            <p><strong>Pickup Location:</strong> <%= booking.getPickupLocation() %></p>
            <p><strong>Dropoff Location:</strong> <%= booking.getDropoffLocation() %></p>
            <p><strong>Total Charge:</strong> RM <%= booking.getTotalCharge() %></p>
        </div>

        <div class="btn-container">
            <!-- Confirm Booking -->
            <form action="ListAvailableTransportServlet" method="get" style="margin: 0;">
                <button type="submit" class="confirm-btn">Confirm</button>
            </form>

            <!-- Cancel Booking -->
            <form action="CancelBookingServlet" method="post" style="margin: 0;">
                <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                <button type="submit" class="cancel-btn">Cancel</button>
            </form>
        </div>
    </div>
</body>
</html>
