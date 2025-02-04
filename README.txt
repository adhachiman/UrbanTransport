# README: Setting Up the Vehicle Booking Database in NetBeans (JavaDB)

## Database Details:
- **Database Name**: `vehicle_booking`
- **Username**: `app`
- **Password**: `app`

## Steps to Create the Database in NetBeans using JavaDB

### Step 1: Start JavaDB in NetBeans
1. Open NetBeans.
2. Go to `Services` tab (Window > Services if not visible).
3. Expand `Databases` and right-click on `Java DB`.
4. Click `Start Server`.

### Step 2: Create a New Database
1. In the `Services` tab, right-click on `Java DB` and select `Create Database`.
2. Enter the following details:
   - **Database Name**: `vehicle_booking`
   - **Username**: `app`
   - **Password**: `app`
   - Check `Remember Password`
3. Click `OK` to create the database.

### Step 3: Connect to the Database
1. In the `Services` tab, expand `Databases`.
2. Right-click on `Java DB (Embedded)` and select `Connect Using...`.
3. Select `vehicle_booking` and provide the credentials:
   - **Username**: `app`
   - **Password**: `app`
4. Click `OK` to establish the connection.

### Step 4: Execute SQL Script to Create Tables
1. Right-click on the connected `vehicle_booking` database and select `Execute Command`.
2. Copy and paste the following SQL script into the SQL editor:

---------------------------------------------------------------------------------------------------

CREATE TABLE customer (
    customer_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE admin (
    admin_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE vehicle (
    vehicle_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    vehicle_type VARCHAR(50) NOT NULL,
    vehicle_charge DECIMAL(10,2) NOT NULL
);

CREATE TABLE booking (
    booking_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    customer_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    pickup_time TIMESTAMP NOT NULL,
    pickup_location VARCHAR(255) NOT NULL,
    dropoff_location VARCHAR(255) NOT NULL,
    distance DECIMAL(10,2) NOT NULL,
    total_charge DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id)
);

CREATE TABLE dashboard_summary (
    summary_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    total_customers INT DEFAULT 0,
    total_admins INT DEFAULT 0,
    total_vehicles INT DEFAULT 0,
    total_bookings INT DEFAULT 0
);

CREATE TABLE distance_map (
    map_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    place_a VARCHAR(255) NOT NULL,
    place_b VARCHAR(255) NOT NULL,
    distance_place DECIMAL(10,2) NOT NULL,
    UNIQUE (place_a, place_b)
);


---------------------------------------------------------------------------------------------

3. Click `Run` or press `Ctrl + Shift + E` to execute the script and create the tables.

### Step 5: Verify Table Creation
1. Expand the `vehicle_booking` database in `Services`.
2. Expand `Tables` and verify that all tables are listed.

### Step 6: Insert Sample Data (Optional)
To test the database, you can insert some sample data using SQL `INSERT` statements.

### Step 7: Use the Database in Your Java Application
- In your Java project, add the `Java DB (Derby)` library.
- Use JDBC to connect to `jdbc:derby://localhost:1527/vehicle_booking` with the provided username and password.

Your database is now set up and ready for development!

