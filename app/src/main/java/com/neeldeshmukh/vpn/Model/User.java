package com.neeldeshmukh.vpn.Model;

public class User {
    private String uuid;
    private String Name;
    private String Email;
    private String Password;

    public User(){

    }
    public User(String uuid, String name, String email, String password) {
        this.uuid = uuid;
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    public User(String uuid, String name, String email) {
        this.uuid = uuid;
        Name = name;
        Email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
