package com.mtb.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "USER_TYPE")
public class UserType {
    @Id
    @Column(name = "TYPE_ID", nullable = false)
    private String typeId;

    @Column(name = "NAME", nullable = false)
    private String typeName;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
