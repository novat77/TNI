package com.sincera.intern.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "site")
public class Site {

    @Id
    @Column(name = "site_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer siteId;

//    @NonNull(message = "Name cannot be empty.")
    @Column(name = "siteName")
    private String siteName;

    @Column(name = "status")
    private String status;

    @Column(name = "siteType")
    private String siteType;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "pin")
    private Integer pin;

    @Column(name="createdBy")
    private String createdBy;
//    @CreationTimestamp
    @Column(name="createdAt")
    private LocalDate createdAt;


    @Column(name="lastModifiedAt")
    private LocalDate lastModifiedAt;


    public String getCreatedBy() {return createdBy;}
    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}
    public void setCreatedAt(LocalDate createdAt) {this.createdAt = createdAt;}
    public void setLastModifiedAt(LocalDate lastModifiedAt) {this.lastModifiedAt = lastModifiedAt;}
    public LocalDate getCreatedAt() {return createdAt;}
    public LocalDate getLastModifiedAt() {return lastModifiedAt;}
    public Integer getSiteId() {return siteId;}
    public void setSiteId(Integer siteId) {this.siteId = siteId;}

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
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
        return "Site{" +
                "instId=" + siteId +
                ", name='" + siteName + '\'' +
                ", status='" + status + '\'' +
                ", type='" + siteType + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pin=" + pin +
                '}';
    }
}
