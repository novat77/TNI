package com.sincera.intern.dto;

import javax.validation.constraints.NotEmpty;

public class ShelfDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Name cannot be empty")
    private String status;
    @NotEmpty(message = "Name cannot be empty")
    private String type;
    private String serialNumber;
    private String parentSite;
    private Integer parentSiteInstId;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getSerialNumber() {return serialNumber;}
    public void setSerialNumber(String serialNumber) {this.serialNumber = serialNumber;}
    public String getParentSite() {return parentSite;}
    public void setParentSite(String parentSite) {this.parentSite = parentSite;}
    public Integer getParentSiteInstId() {return parentSiteInstId;}
    public void setParentSiteInstId(Integer parentSiteInstId) {this.parentSiteInstId = parentSiteInstId;}
    @Override
    public String toString() {
        return "ShelfDto{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", parentSite='" + parentSite + '\'' +
                ", parentSiteInstId=" + parentSiteInstId +
                '}';
    }

    public void setErrorMessage(String s) {
    }
}
