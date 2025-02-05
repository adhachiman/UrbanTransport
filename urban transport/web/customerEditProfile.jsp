<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Retrieve customer data from session
    model.Customer customer = (model.Customer) session.getAttribute("loggedInCustomer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    // Debug: Output the customerId to the page
    System.out.println("Customer ID: " + customer.getCustomerId());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #4CAF50, #81C784);
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: #ffffff;
            color: #333;
            width: 90%;
            max-width: 400px;
            text-align: center;
            padding: 30px 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 2em;
            margin-bottom: 20px;
            color: #4CAF50;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            text-align: left;
            font-weight: bold;
            color: #555;
        }

        input {
            width: 100%;
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        button {
            background: #4CAF50;
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 1em;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        button:hover {
            background: #45a049;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .back-link {
            display: block;
            margin-top: 20px;
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        window.onload = function () {
            let successMessage = "<%= request.getAttribute("successMessage") != null ? request.getAttribute("successMessage") : "" %>";
            let errorMessage = "<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>";

            if (successMessage) {
                alert(successMessage);
            } else if (errorMessage) {
                alert(errorMessage);
            }
        };
    </script>
</head>
<body>
    <div class="container">
        <h1>Edit Profile</h1>
        <form action="EditProfileServlet" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" value="<%= customer.getFullName() %>" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= customer.getEmail() %>" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%= customer.getPassword() %>" required>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" value="<%= customer.getPhone() %>" required>

            <button type="submit">Update</button>
        </form>
        <a href="ListAvailableTransportServlet" class="back-link">Back to Home</a>
    </div>
</body>
</html>
