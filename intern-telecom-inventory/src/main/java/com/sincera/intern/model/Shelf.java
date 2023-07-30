package com.sincera.intern.model;

import javax.persistence.*;

@Entity
@Table(name = "shelf")
public class Shelf {

    @Id
    @Column(name = "inst_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "serialNumber")
    private String serialNumber;
    @Column(name = "parentSite")
    private String parentSite;

    @Column(name = "parentSiteInstId")
    private Integer parentSiteInstId;


    public int getInstId() {return instId;
    }
    public void setInstId(int instId) {this.instId = instId;
    }
    public String getName() {return name;
    }
    public void setName(String name) {this.name = name;
    }
    public String getStatus() {return status;
    }
    public void setStatus(String status) {this.status = status;
    }
    public String getType() {return type;
    }
    public void setType(String type) {this.type = type;
    }
    public String getSerialNumber() {return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {this.serialNumber = serialNumber;
    }
    public String getParentSite() {return parentSite;
    }
    public void setParentSite(String parentSite) {this.parentSite = parentSite;
    }
    public Integer getParentSiteInstId() {return parentSiteInstId;
    }
    public void setParentSiteInstId(Integer parentSiteInstId) {this.parentSiteInstId = parentSiteInstId;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "instId=" + instId +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", parentSite='" + parentSite + '\'' +
                ", parentSiteInstId=" + parentSiteInstId +
                '}';
    }
}
