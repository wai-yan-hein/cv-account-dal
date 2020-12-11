/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.inv.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "unit_relation")
public class UnitRelation implements Serializable {

    @EmbeddedId
    private RelationKey unitKey;
    @Column(name = "factor")
    private Float factor;
   
    public RelationKey getUnitKey() {
        return unitKey;
    }

    public void setUnitKey(RelationKey unitKey) {
        this.unitKey = unitKey;
    }

    public Float getFactor() {
        return factor;
    }

    public void setFactor(Float factor) {
        this.factor = factor;
    }

   

}
