package model;

public class Vehicle {
    private int id;
    private String type;
    private double charge;

    public Vehicle(int id, String type, double charge) {
        this.id = id;
        this.type = type;
        this.charge = charge;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public double getCharge() {
        return charge;
    }
}
