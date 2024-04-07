package com.mtb.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "STATUS")
public class Status {
    @Id
    @Column(name = "STATUS_ID", nullable = false)
    private String statusId;

    @Column(name = "DESC", nullable = false)
    private String statusDescription;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
