package com.sincera.intern.dto;

public class CardDto {


    private String CardName;


//    private String CardSerialNumber;

    private int NetworkID;

    private int SlotID;

    private String SlotName;

    private int ShelfID;

    private String ShelfName;

    private int ParentSiteID;

    private String ParentSiteName;


    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

 /*   public String getCardSerialNumber() {
        return CardSerialNumber;
    }

    public void setCardSerialNumber(String cardSerialNumber) {
        CardSerialNumber = cardSerialNumber;
    }*/

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

                ", CardName='" + CardName + '\'' +
//                ", CardSerialNumber='" + CardSerialNumber + '\'' +
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
