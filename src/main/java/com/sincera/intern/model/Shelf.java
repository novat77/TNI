package com.sincera.intern.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shelf")
public class Shelf {

    @Id
    @Column(name = "shelf_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shelfId;

    @Column(name = "shelfName")
    private String shelfName;

    @Column(name = "status")
    private String status;

    @Column(name = "shelfType")
    private String shelfType;
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "model")
    private  Integer model;

    @Column(name = "serialNumber")
    private String serialNumber;
    @Column(name = "parentSite")
    private String parentSite;

    @Column(name = "parentSiteInstId")
    private Integer parentSiteInstId;
    @Column(name="createdBy")
    private String createdBy;
    //    @CreationTimestamp
    @Column(name="createdAt")
    private LocalDate createdAt;


    @Column(name="lastModifiedAt")
    private LocalDate lastModifiedAt;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDate lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }



    @Override
    public String toString() {
        return "Shelf{" +
                "instId=" + shelfId +
                ", name='" + shelfName + '\'' +
                ", status='" + status + '\'' +
                ", type='" + shelfType + '\'' +
                ", vendor='" + vendor + '\'' +
                ", model=" + model +
                ", serialNumber='" + serialNumber + '\'' +
                ", parentSite='" + parentSite + '\'' +
                ", parentSiteInstId=" + parentSiteInstId +
                '}';
    }

    public Integer getShelfId() {
        return shelfId;
    }

    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShelfType() {
        return shelfType;
    }

    public void setShelfType(String shelfType) {
        this.shelfType = shelfType;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getParentSite() {
        return parentSite;
    }

    public void setParentSite(String parentSite) {
        this.parentSite = parentSite;
    }

    public Integer getParentSiteInstId() {
        return parentSiteInstId;
    }

    public void setParentSiteInstId(Integer parentSiteInstId) {
        this.parentSiteInstId = parentSiteInstId;
    }
}
