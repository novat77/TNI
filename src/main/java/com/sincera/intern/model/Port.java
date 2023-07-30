package com.sincera.intern.model;

import javax.persistence.*;

@Entity
@Table(name = "port")
public class Port {

    @Id
    @Column(name = "port_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer portId;
    @Column(name = "portName")
    private String portName;

    @Column(name = "portType")
    private String portType;
    @Column(name = "ipAddress")
    private String ipAddress;
    @Column(name = "bandwidth")
    private String bandwidth;
    @Column(name = "trail")
    private String trail;
    @Column(name = "parentCardId")
    private Integer parentCardId;
    @Column(name = "parentCardName")
    private String parentCardName;

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
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
        return "Port{" +
                "portId=" + portId +
                ", portName='" + portName + '\'' +
                ", portType='" + portType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", bandwidth='" + bandwidth + '\'' +
                ", trail='" + trail + '\'' +
                ", parentCardId=" + parentCardId +
                ", parentCardName='" + parentCardName + '\'' +
                '}';
    }
}
