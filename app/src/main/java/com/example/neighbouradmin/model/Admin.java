package com.example.neighbouradmin.model;

public class Admin {

    private String AdminId;
    private String Username;
    private String password;

    public Admin(String AdminId, String password){
        this.AdminId = AdminId;
        this.password = password;
    }

    public  Admin(){}

    public void setAdminId(String AdminId){
        this.AdminId = AdminId;
    }

    public String getAdminId(){
        return  AdminId;
    }

    public void setPassword(String password){
        this.password =password;
    }

    public String getPassword(){
        return password;
    }
}
