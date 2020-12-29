/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author winswe
 */
@Entity
@Table(name = "privilege_h2")
public class PrivilegeH2 implements java.io.Serializable {

    private PrivilegeKeyH2 key;
    private Boolean isAllow;

    @EmbeddedId
    public PrivilegeKeyH2 getKey() {
        return key;
    }

    public void setKey(PrivilegeKeyH2 key) {
        this.key = key;
    }

    @Column(name = "allow")
    public Boolean getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(Boolean isAllow) {
        this.isAllow = isAllow;
    }

}
