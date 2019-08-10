package com.example.neighbouradmin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Resident implements Parcelable {

    private String userId;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private String photo;
    private Integer verify;

    public Resident(String userId, String username, String email, String address, String phoneNumber, String password, String photo, Integer verify)
    {
        this.userId=userId;
        this.username=username;
        this.email=email;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.password=password;
        this.photo=photo;
        this.verify=verify;
    }

    public Resident()  {}

    protected Resident(Parcel in){

        userId = in.readString();
        username = in.readString();
        email = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        password = in.readString();
    }

    public static final Parcelable.Creator<Resident> CREATOR = new Parcelable.Creator<Resident>() {
        @Override
        public Resident createFromParcel(Parcel in) {
            return new Resident(in);
        }

        @Override
        public Resident[] newArray(int size) {
            return new Resident[size];
        }
    };

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhoto() {

        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getUsername() {

        return username;
    }

    public String getAddress() {

        return address;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }


    public void setPassword(String password) {

        this.password = password;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public Integer getVerify(){
        return verify;
    }

    public void setVerify(Integer verify){
        this.verify=verify;
    }

    @Override
    public String toString(){
        return "User{" +
                "email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", verify='" + verify + '\'' +
                '}';
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(userId);
        dest.writeString(username);
        dest.writeString(address);
        dest.writeString(password);
        dest.writeString(phoneNumber);
    }
}

