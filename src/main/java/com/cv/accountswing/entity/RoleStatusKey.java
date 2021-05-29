/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity;

import java.io.Serializable;
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
}
