package com.sincera.intern.model;

import javax.persistence.*;

@Entity
@Table(name = "Port")
public class Port {

    @Id
    @Column(name = "PortID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PortID;
    @Column(name = "PortName")
    private String PortName;

    @Column(name = "PortType")
    private String PortType;
    @Column(name = "IPAdderss")
    private String IPAdderss;
    @Column(name = "Bandwidth")
    private String Bandwidth;
    @Column(name = "Trail")
    private String Trail;
    @Column(name = "ParentCardID")
    private int ParentCardID;
    @Column(name = "ParentCardName")
    private String ParentCardName;


    public int getPortID() {
        return PortID;
    }

    public void setPortID(int portID) {
        PortID = portID;
    }

    public String getPortName() {
        return PortName;
    }

    public void setPortName(String portName) {
        PortName = portName;
    }

    public String getPortType() {
        return PortType;
    }

    public void setPortType(String portType) {
        PortType = portType;
    }

    public String getIPAdderss() {
        return IPAdderss;
    }

    public void setIPAdderss(String IPAdderss) {
        this.IPAdderss = IPAdderss;
    }

    public String getBandwidth() {
        return Bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        Bandwidth = bandwidth;
    }

    public String getTrail() {
        return Trail;
    }

    public void setTrail(String trail) {
        Trail = trail;
    }

    public int getParentCardID() {
        return ParentCardID;
    }

    public void setParentCardID(int parentCardID) {
        ParentCardID = parentCardID;
    }

    public String getParentCardName() {
        return ParentCardName;
    }

    public void setParentCardName(String parentCardName) {
        ParentCardName = parentCardName;
    }
    @Override
    public String toString() {
        return "Port{" +
                "PortID=" + PortID +
                ", PortName='" + PortName + '\'' +
                ", PortType='" + PortType + '\'' +
                ", IPAdderss='" + IPAdderss + '\'' +
                ", Bandwidth='" + Bandwidth + '\'' +
                ", Trail='" + Trail + '\'' +
                ", ParentCardID=" + ParentCardID +
                ", ParentCardName='" + ParentCardName + '\'' +
                '}';
    }

}
