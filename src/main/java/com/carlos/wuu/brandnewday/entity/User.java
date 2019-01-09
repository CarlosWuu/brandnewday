package com.carlos.wuu.brandnewday.entity;

import com.carlos.wuu.brandnewday.enumEntity.AddressTypeEnum;
import com.carlos.wuu.brandnewday.handler.AddressTypeHandler;

public class User {
    private int id;
    private AddressTypeEnum address;
    private String password;
    private String userName;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddressTypeEnum getAddress() {
        return address;
    }

    public void setAddress(AddressTypeEnum address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
