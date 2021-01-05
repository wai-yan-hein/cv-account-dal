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
 * @author winswe
 */
@Embeddable
public class UsrCompRoleKey implements Serializable {

    @Column(name = "user_code")
    private String userCode;
    @Column(name = "comp_code")
    private String compCode;
    @Column(name = "role_code")
    private String roleCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.userCode);
        hash = 71 * hash + Objects.hashCode(this.compCode);
        hash = 71 * hash + Objects.hashCode(this.roleCode);
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
        final UsrCompRoleKey other = (UsrCompRoleKey) obj;
        if (!Objects.equals(this.userCode, other.userCode)) {
            return false;
        }
        if (!Objects.equals(this.compCode, other.compCode)) {
            return false;
        }
        if (!Objects.equals(this.roleCode, other.roleCode)) {
            return false;
        }
        return true;
    }
}
