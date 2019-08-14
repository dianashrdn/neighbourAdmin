package com.example.neighbouradmin.model;

public class Admin {

    private String adminId;
    private String username;
    private String password;
    private String email;

    public Admin(String adminId, String password){
        this.adminId = adminId;
        this.password = password;
    }

    public  Admin(){}

    public void setAdminId(String adminId){
        this.adminId = adminId;
    }

    public String getAdminId(){
        return  adminId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return  username;
    }

    public void setPassword(String password){
        this.password =password;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        this.email =email;
    }

    public String getEmail(){
        return email;
    }
}
