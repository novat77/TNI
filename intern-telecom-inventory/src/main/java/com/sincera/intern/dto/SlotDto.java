package com.sincera.intern.dto;

public class SlotDto {


    private String SoltName;
    private int ParentShelfID;
    private String ParentShelfName;
    private int ParentSitefID;
    private String ParentSitefName;


    public String getSoltName() {
        return SoltName;
    }

    public void setSoltName(String soltName) {
        SoltName = soltName;
    }

    public int getParentShelfID() {
        return ParentShelfID;
    }

    public void setParentShelfID(int parentShelfID) {
        ParentShelfID = parentShelfID;
    }

    public String getParentShelfName() {
        return ParentShelfName;
    }

    public void setParentShelfName(String parentShelfName) {
        ParentShelfName = parentShelfName;
    }

    public int getParentSitefID() {
        return ParentSitefID;
    }

    public void setParentSitefID(int parentSitefID) {
        ParentSitefID = parentSitefID;
    }

    public String getParentSitefName() {
        return ParentSitefName;
    }

    public void setParentSitefName(String parentSitefName) {
        ParentSitefName = parentSitefName;
    }

    @Override
    public String toString() {
        return "Slot{" +
                ", SoltName='" + SoltName + '\'' +
                ", ParentShelfID=" + ParentShelfID +
                ", ParentShelfName='" + ParentShelfName + '\'' +
                ", ParentSitefID=" + ParentSitefID +
                ", ParentSitefName='" + ParentSitefName + '\'' +
                '}';
    }
}
