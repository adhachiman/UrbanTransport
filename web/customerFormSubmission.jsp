<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Form</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fb;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            max-width: 800px;
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
            margin-bottom: 20px;
        }

        p {
            font-size: 1.2em;
            color: #7f8c8d;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            font-size: 1.1em;
            color: #2c3e50;
            margin-top: 10px;
            display: block;
        }

        input[type="text"], input[type="datetime-local"],input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 6px;
            background-color: #f9f9f9;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: white;
            padding: 12px 20px;
            font-size: 1.2em;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 20px;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        .form-info {
            margin: 20px 0;
            font-size: 1.1em;
            color: #34495e;
            text-align: center;
        }

        .back-link {
            display: block;
            text-align: center;
            font-size: 1.1em;
            color: #3498db;
            text-decoration: none;
            margin-top: 20px;
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
        <h1>Booking Form</h1>
        
        <c:if test="${vehicle != null}">
            <form action="FormSubmissionServlet" method="post">
                <input type="hidden" name="vehicleId" value="${vehicle.id}" />

                <div class="form-info">
                    <p>Vehicle Type: <strong>${vehicle.type}</strong></p>
                    <p>Charge: RM <strong>${vehicle.charge}</strong></p>
                </div>

                <label for="pickupTime">Pickup Time:</label>
                <input type="datetime-local" id="pickupTime" name="pickupTime" required /><br/>

                <label for="pickupLocation">Pickup Location:</label>
                <input type="text" id="pickupLocation" name="pickupLocation" required /><br/>

                <label for="dropoffLocation">Drop-off Location:</label>
                <input type="text" id="dropoffLocation" name="dropoffLocation" required /><br/>
                
                <label for="distance">Distance (km):</label>
                <input type="number" step="0.01" id="distance" name="distance" required><br/>


                <input type="submit" value="Submit Booking" />
            </form>

            <a href="ListAvailableTransportServlet" class="back-link">Back to List</a>
        </c:if>

    </div>

</body>
</html>
