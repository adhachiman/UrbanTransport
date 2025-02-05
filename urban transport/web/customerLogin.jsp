<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #4CAF50, #81C784);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #fff;
        }

        .login-container {
            background: #ffffff;
            color: #333;
            width: 90%;
            max-width: 400px;
            text-align: center;
            padding: 30px 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h2 {
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
            font-size: 1em;
            text-align: left;
        }

        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
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

        input[type="submit"]:hover {
            background: #45a049;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .register-link {
            margin-top: 20px;
            font-size: 0.9em;
        }

        .register-link a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Customer Login</h2>
        <form action="CustomerLoginServlet" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
            
            <input type="submit" value="Login">
        </form>
        <p class="register-link">Don't have an account? <a href="customerRegister.jsp">Register here</a></p>
    </div>
</body>
</html>
