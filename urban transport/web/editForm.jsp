<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="DAO.AdminDAO" %> <!-- Add import for AdminDAO -->
<%@ page import="DAO.CustomerDAO" %> <!-- Add import for CustomerDAO -->
<%@ page import="model.Admin" %> <!-- Add import for Admin model -->
<%@ page import="model.Customer" %> <!-- Add import for Customer model -->

<%
    String userType = request.getParameter("userType");
    String userIdStr = request.getParameter("userId");
    model.Admin admin = null;
    model.Customer customer = null;

    // Check if userId is present and valid
    if (userIdStr != null && !userIdStr.isEmpty()) {
        try {
            int userId = Integer.parseInt(userIdStr);

            if ("admin".equals(userType)) {
                admin = AdminDAO.getAdminById(userId);
            } else if ("customer".equals(userType)) {
                customer = CustomerDAO.getCustomerById(userId);
            }
        } catch (NumberFormatException e) {
            out.println("<p>Error: Invalid user ID format.</p>");
            e.printStackTrace();
        }
    } else {
        out.println("<p>Error: Missing user ID.</p>");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #2196F3, #64B5F6);
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
            color: #2196F3;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            text-align: left;
            font-size: 1em;
            color: #555;
        }

        input {
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%;
            box-sizing: border-box;
        }

        button {
            background: #2196F3;
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
            background: #1976D2;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .history-link {
            display: inline-block;
            margin-top: 20px;
            color: #2196F3;
            text-decoration: none;
            font-size: 1em;
        }

        .history-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1><%= "admin".equals(userType) ? "Update Admin" : "Update Customer" %></h1>
        <form action="EditUsersServlet" method="post">
            <!-- Pass the userId and userType to the servlet -->
            <input type="hidden" name="userId" value="<%= userIdStr %>">
            <input type="hidden" name="userType" value="<%= userType %>">

            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" 
                   value="<%= admin != null ? admin.getFullName() : customer.getFullName() %>" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" 
                   value="<%= admin != null ? admin.getEmail() : customer.getEmail() %>" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" 
                   value="<%= admin != null ? admin.getPassword() : customer.getPassword() %>" required>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" 
                   value="<%= admin != null ? admin.getPhoneNumber() : customer.getPhone() %>" required>

            <button type="submit" name="action" value="update">Update</button>
        </form>
        
        <!-- Back to the users edit page link -->
        <a href="EditUsersServlet" class="history-link">Back to users edit page</a>
    </div>
</body>
</html>
