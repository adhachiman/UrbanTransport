<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Choose User Type</title>
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

        h2 {
            font-size: 2em;
            margin-bottom: 20px;
            color: #4CAF50;
        }

        .button-container {
            margin-top: 20px;
            display: flex;
            justify-content: center;
            gap: 20px;
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
    </style>
</head>
<body>
    <div class="container">
        <h2>Select Your Role</h2>
        <p>Please choose your role to proceed:</p>
        <div class="button-container">
            <button onclick="location.href='customerLogin.jsp'">Customer</button>
            <button onclick="location.href='adminLogin.jsp'">Admin</button>
        </div>
    </div>
</body>
</html>
