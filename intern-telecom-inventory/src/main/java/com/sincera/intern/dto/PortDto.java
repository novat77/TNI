package com.sincera.intern.dto;

public class PortDto {

    private String PortName;
    private String PortType;
    private String IPAdderss;
    private String Bandwidth;
    private String Trail;
    private int ParentCardID;
    private String ParentCardName;



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
