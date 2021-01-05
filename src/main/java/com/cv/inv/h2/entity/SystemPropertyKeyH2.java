/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity;

import com.cv.accountswing.entity.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author winswe
 */
@Embeddable
public class SystemPropertyKeyH2 implements Serializable{
    private String propKey;
    private Integer compCode;

    public SystemPropertyKeyH2() {
    }
    
    public SystemPropertyKeyH2(String propKey, Integer compCode) {
        this.propKey = propKey;
        this.compCode = compCode;
    }
    
    @Column(name="prop_key", nullable=false)
    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    @Column(name="comp_code")
    public Integer getCompCode() {
        return compCode;
    }

    public void setCompCode(Integer compCode) {
        this.compCode = compCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.propKey);
        hash = 37 * hash + Objects.hashCode(this.compCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemPropertyKeyH2 other = (SystemPropertyKeyH2) obj;
        if (!Objects.equals(this.propKey, other.propKey)) {
            return false;
        }
        if (!Objects.equals(this.compCode, other.compCode)) {
            return false;
        }
        return true;
    }
    
    
}
