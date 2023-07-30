package com.sincera.intern.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SlotDto {
    private String errorMessage;
    private Integer slotId;
    @NotEmpty(message = "SlotName cannot be empty")
    private String slotName;
    private Integer parentShelfId;
    @NotEmpty(message = "Parent Shelf name cannot be empty")
    private String parentShelfName;
    private Integer parentSiteId;
    @NotEmpty(message = "Parent Site name cannot be empty")
    private String parentSiteName;



    private String createdBy;
    //    @CreationTimestamp
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;

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

    public Integer getParentShelfId() {
        return parentShelfId;
    }

    public void setParentShelfId(Integer parentShelfId) {
        this.parentShelfId = parentShelfId;
    }

    public String getParentShelfName() {
        return parentShelfName;
    }

    public void setParentShelfName(String parentShelfName) {
        this.parentShelfName = parentShelfName;
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

    @Override
    public String toString() {
        return "SlotDto{" +
//                ", slotId=" + slotId +

                ", slotName='" + slotName + '\'' +
                ", parentShelfId=" + parentShelfId +
                ", parentShelfName='" + parentShelfName + '\'' +
                ", parentSiteId=" + parentSiteId +
                ", parentSiteName='" + parentSiteName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }

    public String getErrorMessage() {return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;
    }

}
