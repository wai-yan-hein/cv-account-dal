/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity;

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
@Table(name = "role_status")
public class RoleStatus implements Serializable {

    @EmbeddedId
    private RoleStatusKey roleKey;
    @Column(name = "status")
    private Boolean status;

    public RoleStatusKey getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(RoleStatusKey roleKey) {
        this.roleKey = roleKey;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
