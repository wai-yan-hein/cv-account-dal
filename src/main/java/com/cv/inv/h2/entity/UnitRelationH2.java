/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity;

import com.cv.inv.entity.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "unit_relation_h2")
public class UnitRelationH2 implements Serializable {

    @EmbeddedId
    private RelationKeyH2 unitKey;
    @Column(name = "factor")
    private Float factor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    public RelationKeyH2 getUnitKey() {
        return unitKey;
    }

    public void setUnitKey(RelationKeyH2 unitKey) {
        this.unitKey = unitKey;
    }

    public Float getFactor() {
        return factor;
    }

    public void setFactor(Float factor) {
        this.factor = factor;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}
