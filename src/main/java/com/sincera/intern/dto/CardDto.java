package com.sincera.intern.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CardDto {

    private Integer cardId;
    @NotEmpty(message = "Card name cannot be empty")
    private String cardName;

    @NotNull(message = "Card serial number cannot be empty")
    private String cardSerialNumber;

    @NotNull(message = "Network Id cannot be empty")
    private Integer networkId;

    private Integer slotId;

    @NotEmpty(message = "Slot name cannot be empty")
    private String slotName;

    private Integer shelfId;

    @NotEmpty(message = "Shelf name cannot be empty")
    private String shelfName;

    private Integer parentSiteId;

    @NotEmpty(message = "Parent site name cannot be empty")
    private String parentSiteName;

    private String createdBy;
    //    @CreationTimestamp
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
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
    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardSerialNumber() {
        return cardSerialNumber;
    }

    public void setCardSerialNumber(String cardSerialNumber) {
        this.cardSerialNumber = cardSerialNumber;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
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

    public Integer getParentSiteId() {
        return parentSiteId;
    }

    public void setParentSiteId(Integer parentSiteId) {
        this.parentSiteId = parentSiteId;
    }

    public String getParentSiteName() {
        return parentSiteName;
    }

    public void setParentSiteName(String parentSiteName) {
        this.parentSiteName = parentSiteName;
    }

    @Override
    public String toString() {
        return "CardDto{" +
                "cardId=" + cardId +
                ", cardName='" + cardName + '\'' +
                ", cardSerialNumber='" + cardSerialNumber + '\'' +
                ", networkId=" + networkId +
                ", slotId=" + slotId +
                ", slotName='" + slotName + '\'' +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", parentSiteId=" + parentSiteId +
                ", parentSiteName='" + parentSiteName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }

    public void setErrorMessage(String s) {
    }
}
