package com.sincera.intern.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PortDto {
    private Integer portId;
    @NotEmpty(message = "Port name cannot be empty")
    private String portName;

    @NotEmpty(message = "Port type cannot be empty")
    private String portType;

    @NotEmpty(message = "IP address cannot be empty")
    private String ipAddress;

    @NotEmpty(message = "Bandwidth cannot be empty")
    private String bandwidth;

    @NotEmpty(message = "Trail cannot be empty")
    private String trail;

    private Integer parentCardId;

    @NotEmpty(message = "Parent card name cannot be empty")
    private String parentCardName;

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    public String getPortName() {
        return portName;
    }
    public void setPortName(String portName) {
        this.portName = portName;
    }
    public String getPortType() {
        return portType;
    }
    public void setPortType(String portType) {
        this.portType = portType;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getBandwidth() {
        return bandwidth;
    }
    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }
    public String getTrail() {
        return trail;
    }
    public void setTrail(String trail) {
        this.trail = trail;
    }
    public Integer getParentCardId() {
        return parentCardId;
    }
    public void setParentCardId(Integer parentCardId) {
        this.parentCardId = parentCardId;
    }
    public String getParentCardName() {
        return parentCardName;
    }
    public void setParentCardName(String parentCardName) {
        this.parentCardName = parentCardName;
    }
    @Override
    public String toString() {
        return "PortDto{" +
                "portName='" + portName + '\'' +
                "portId='" +portId + '\'' +
                ", portType='" + portType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", bandwidth='" + bandwidth + '\'' +
                ", trail='" + trail + '\'' +
                ", parentCardId=" + parentCardId +
                ", parentCardName='" + parentCardName + '\'' +
                '}';
    }

    public void setErrorMessage(String s) {
    }
}
