/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity;

import com.cv.accountswing.entity.*;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author WSwe
 */
@Entity
@Table(name = "sys_prop_h2")
public class SystemPropertyH2 implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private SystemPropertyKeyH2 key;
    private String propValue;
    private String remark;

    @EmbeddedId
    public SystemPropertyKeyH2 getKey() {
        return key;
    }

    public void setKey(SystemPropertyKeyH2 key) {
        this.key = key;
    }
        
    @Column(name="prop_value")
    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    @Column(name="remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
