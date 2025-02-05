<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .registration-container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        label {
            font-size: 1rem;
            color: #555;
            display: block;
            margin-bottom: 8px;
            text-align: left;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 1.1rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        p {
            font-size: 0.9rem;
            color: #555;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .registration-container {
                padding: 20px;
            }

            h2 {
                font-size: 1.2rem;
            }

            input[type="submit"] {
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>
    <div class="registration-container">
        <h2>Admin Registration</h2>
        <form action="AdminRegisterServlet" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" name="fullName" id="fullName" required><br><br>

            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required><br><br>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required><br><br>

            <label for="phone">Phone Number:</label>
            <input type="text" name="phone" id="phone" required><br><br>

            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
