package com.sincera.intern.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class ShelfDto {


    private Integer shelfId;
    @NotEmpty(message = "ShelfName cannot be empty")
    private String shelfName;
//    @NotEmpty(message = "status cannot be empty")
//    private String status;
    @NotEmpty(message = "ShelfType cannot be empty")
    private String shelfType;
    private String vendor;
    private  Integer model;
    private String serialNumber;
    private String parentSite;
    private Integer parentSiteInstId;
    private String createdBy;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;

    @Override
    public String toString() {
        return "ShelfDto{" +
                "shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfType='" + shelfType + '\'' +
                ", vendor='" + vendor + '\'' +
                ", model=" + model +
                ", serialNumber='" + serialNumber + '\'' +
                ", parentSite='" + parentSite + '\'' +
                ", parentSiteInstId=" + parentSiteInstId +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

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

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

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

    private String errorMessage;

    public String getErrorMessage() {return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;
    }
}
