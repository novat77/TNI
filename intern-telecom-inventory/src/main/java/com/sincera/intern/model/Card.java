package com.sincera.intern.model;

import javax.persistence.*;

@Entity
@Table(name = "Card")
public class Card {
    @Id
    @Column(name = "CardID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CardID;

    @Column(name = "CardName")
    private String CardName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CardSerialNumber")
    private  String CardSerialNumber;

    @Column(name = "NetworkID")
    private int NetworkID;

    @Column(name = "SlotID")
    private int SlotID;

    @Column(name = "SlotName")
    private String SlotName;

    @Column(name = "ShelfID")
    private int ShelfID;

    @Column(name ="ShelfName")
    private String ShelfName;
    @Column(name = "ParentSiteID")
    private int ParentSiteID;
    @Column(name = "ParentSiteName")
    private String ParentSiteName;

    public int getCardID() {
        return CardID;
    }

    public void setCardID(int cardID) {
        CardID = cardID;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public String getCardSerialNumber() {
        return CardSerialNumber;
    }

    public void setCardSerialNumber(String cardSerialNumber) {
        CardSerialNumber = cardSerialNumber;
    }

    public int getNetworkID() {
        return NetworkID;
    }

    public void setNetworkID(int networkID) {
        NetworkID = networkID;
    }

    public int getSlotID() {
        return SlotID;
    }

    public void setSlotID(int slotID) {
        SlotID = slotID;
    }

    public String getSlotName() {
        return SlotName;
    }

    public void setSlotName(String slotName) {
        SlotName = slotName;
    }

    public int getShelfID() {
        return ShelfID;
    }

    public void setShelfID(int shelfID) {
        ShelfID = shelfID;
    }

    public String getShelfName() {
        return ShelfName;
    }

    public void setShelfName(String shelfName) {
        ShelfName = shelfName;
    }

    public int getParentSiteID() {
        return ParentSiteID;
    }

    public void setParentSiteID(int parentSiteID) {
        ParentSiteID = parentSiteID;
    }

    public String getParentSiteName() {
        return ParentSiteName;
    }

    public void setParentSiteName(String parentSiteName) {
        ParentSiteName = parentSiteName;
    }

    @Override
    public String toString() {
        return "Card{" +
                "CardID=" + CardID +
                ", CardName='" + CardName + '\'' +
                ", CardSerialNumber='" + CardSerialNumber + '\'' +
                ", NetworkID=" + NetworkID +
                ", SlotID=" + SlotID +
                ", SlotName='" + SlotName + '\'' +
                ", ShelfID=" + ShelfID +
                ", ShelfName='" + ShelfName + '\'' +
                ", ParentSiteID=" + ParentSiteID +
                ", ParentSiteName='" + ParentSiteName + '\'' +
                '}';
    }
}
