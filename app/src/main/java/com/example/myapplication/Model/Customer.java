package com.example.myapplication.Model;

public class Customer {
    private int id;
    private String email;
    private String name;
    private String password;
    private String profilePicUrl;
    private String uID;
    private String token;
    private int idAdmin;

    public Customer(int id, String email, String name, String password, String profilePicUrl, String uID, String token, int idAdmin) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.profilePicUrl = profilePicUrl;
        this.uID = uID;
        this.token = token;
        this.idAdmin = idAdmin;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
}
