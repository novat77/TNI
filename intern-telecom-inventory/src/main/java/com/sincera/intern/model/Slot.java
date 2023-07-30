package com.sincera.intern.model;

import javax.persistence.*;

@Entity
@Table(name = "Slot")
public class Slot {

    @Id
    @Column(name = "SlotID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SlotID;

    @Column(name = "SoltName")
    private String SoltName;

    @Column(name = "ParentShelfID")
    private int ParentShelfID;
    @Column(name = "ParentShelfName")
    private String ParentShelfName;
    @Column(name = "ParentSitefID")
    private int ParentSitefID;
    @Column(name = "ParentSitefName")
    private String ParentSitefName;

    public int getSlotID() {
        return SlotID;
    }

    public void setSlotID(int slotID) {
        SlotID = slotID;
    }

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
                "SlotID=" + SlotID +
                ", SoltName='" + SoltName + '\'' +
                ", ParentShelfID=" + ParentShelfID +
                ", ParentShelfName='" + ParentShelfName + '\'' +
                ", ParentSitefID=" + ParentSitefID +
                ", ParentSitefName='" + ParentSitefName + '\'' +
                '}';
    }
}
