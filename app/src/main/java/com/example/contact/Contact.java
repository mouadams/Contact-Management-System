package com.example.contact;

import androidx.annotation.NonNull;

public class Contact {

    private int id;
    private String Name;
    private String Phone;


    public Contact(int id, String Name,String Phone){
        this.id = id;
        this.Name = Name;
        this.Phone = Phone;
    }

    @NonNull
    @Override
    public String toString() {
        return Name + " - " + Phone ;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void getName(String Name){
        this.Name = Name;
    }

    public void getPhone(String Phone){
        this.Phone = Phone;
    }

    public void setName(String name) {
        Name = name;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }


}