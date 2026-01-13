package com.example.contact.model;

import androidx.annotation.NonNull;

public class Contact {

    private int id;
    private String name;
    private String phone;


    public Contact(int id, String name,String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - " + phone ;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


}