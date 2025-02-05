<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Users</title>
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
            flex-direction: column;
            padding: 20px;
        }

        .container {
            background: #ffffff;
            color: #333;
            width: 100%;
            max-width: 800px;
            text-align: center;
            padding: 30px 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
            color: #4CAF50;
        }

        h2 {
            font-size: 1.8em;
            margin-bottom: 15px;
            color: #006400;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .actions button {
            background: #4CAF50;
            color: white;
            border: none;
            padding: 8px 12px;
            font-size: 1em;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-right: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        .actions button:hover {
            background: #45a049;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .history-link {
            display: inline-block;
            margin-top: 20px;
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        .history-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Manage Users</h1>

        <h2>Customer Data</h2>
        <table>
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.customerId}</td>
                        <td>${customer.fullName}</td>
                        <td>${customer.email}</td>
                        <td>${customer.phone}</td>
                        <td class="actions">
                            <form action="EditUsersServlet" method="post" style="display:inline;">
                                <input type="hidden" name="userId" value="${customer.customerId}">
                                <input type="hidden" name="userType" value="customer">
                                <button type="submit" name="action" value="edit">Edit</button>
                            </form>
                            <form action="EditUsersServlet" method="post" style="display:inline;">
                                <input type="hidden" name="userId" value="${customer.customerId}">
                                <input type="hidden" name="userType" value="customer">
                                <button type="submit" name="action" value="delete">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2>Admin Data</h2>
        <table>
            <thead>
                <tr>
                    <th>Admin ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="admin" items="${admins}">
                    <tr>
                        <td>${admin.adminId}</td>
                        <td>${admin.fullName}</td>
                        <td>${admin.email}</td>
                        <td>${admin.phoneNumber}</td>
                        <td class="actions">
                            <form action="EditUsersServlet" method="post" style="display:inline;">
                                <input type="hidden" name="userId" value="${admin.adminId}">
                                <input type="hidden" name="userType" value="admin">
                                <button type="submit" name="action" value="edit">Edit</button>
                            </form>
                            <form action="EditUsersServlet" method="post" style="display:inline;">
                                <input type="hidden" name="userId" value="${admin.adminId}">
                                <input type="hidden" name="userType" value="admin">
                                <button type="submit" name="action" value="delete">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="AdminDashboardServlet" class="history-link">Back to Dashboard</a>
    </div>
</body>
</html>
