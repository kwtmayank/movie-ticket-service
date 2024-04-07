package com.mtb.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Theatre {

    private String code;
    @NotNull
    @Size(min = 3)
    private String name;
    @NotNull
    private String address;
    @NotNull
    @Size(min = 3)
    private String city;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
