package com.sincera.intern.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SiteDto {

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Status cannot be empty")
    private String status;
    @NotEmpty(message = "Type cannot be empty")
    private String type;
    private String latitude;
    private String longitude;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private Integer pin;
    private String createdBy;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;

    public String getCreatedBy() {return createdBy;}
    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}
    public LocalDate getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDate createdAt) {this.createdAt = createdAt;}
    public LocalDate getLastModifiedAt() {return lastModifiedAt;}
    public void setLastModifiedAt(LocalDate lastModifiedAt) {this.lastModifiedAt = lastModifiedAt;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "SiteDto{" +
                "name='" + name + '\'' +
                ", pin=" + pin +
                '}';
    }


    public void setErrorMessage(String s) {
    }
}
