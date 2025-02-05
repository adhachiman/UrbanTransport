package model;

public class Admin {
    private int adminId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber; // Change field to match the database column name

    // Constructor
    public Admin(int adminId, String fullName, String email, String password, String phoneNumber) {
        this.adminId = adminId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber; // Initialize the correct field
    }

    // Getter and Setter methods
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() { // Change getter to match the new field
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { // Change setter to match the new field
        this.phoneNumber = phoneNumber;
    }
}
