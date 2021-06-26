/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Lenovo
 */
@Embeddable
public class RoleStatusKey implements Serializable {

    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "sts_key")
    private String statsKey;

    public RoleStatusKey(String roleCode, String statsKey) {
        this.roleCode = roleCode;
        this.statsKey = statsKey;
    }

    public RoleStatusKey() {
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getStatsKey() {
        return statsKey;
    }

    public void setStatsKey(String statsKey) {
        this.statsKey = statsKey;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.roleCode);
        hash = 59 * hash + Objects.hashCode(this.statsKey);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoleStatusKey other = (RoleStatusKey) obj;
        if (!Objects.equals(this.roleCode, other.roleCode)) {
            return false;
        }
        if (!Objects.equals(this.statsKey, other.statsKey)) {
            return false;
        }
        return true;
    }

}
