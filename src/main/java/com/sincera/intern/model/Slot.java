package com.sincera.intern.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "slot")
public class Slot {

    @Id
    @Column(name = "slot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slotId;

    @Column(name = "slotName")
    private String slotName;

    @Column(name = "parentShelfId")
    private Integer parentShelfId;
    @Column(name = "parentShelfName")
    private String parentShelfName;
    @Column(name = "parentSiteId")
    private Integer parentSiteId;
    @Column(name = "parentSiteName")
    private String parentSiteName;
    @Column(name="createdBy")
    private String createdBy;
    //    @CreationTimestamp
    @Column(name="createdAt")
    private LocalDate createdAt;


    @Column(name="lastModifiedAt")
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
        return "Slot{" +
//                "slotId=" + slotId +
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
}