package com.connectiondb.model;

public class Client {

    private int id;
    private String full_name;
    private String phoneNumber;
    private String curp;
    
    public Client() {
    }
    public Client(int id, String full_name, String phoneNumber, String curp) {
        this.id = id;
        this.full_name = full_name;
        this.phoneNumber = phoneNumber;
        this.curp = curp;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getCurp() {
        return curp;
    }
    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    @Override
    public String toString() {
        return "Client [id = " + id + ", full_name = " + full_name + ", phoneNumber = " + phoneNumber + ", curp = " + curp
                + "]";
    }
    
}
