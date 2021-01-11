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
public class AssignDeptKey implements Serializable {

    private String compCode;
    private String roleCode;
    private String deptCode;

    @Column(name = "comp_code")
    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    @Column(name = "role_id")
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Column(name = "dept_code", length = 15)
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.compCode);
        hash = 19 * hash + Objects.hashCode(this.roleCode);
        hash = 19 * hash + Objects.hashCode(this.deptCode);
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
        final AssignDeptKey other = (AssignDeptKey) obj;
        if (!Objects.equals(this.compCode, other.compCode)) {
            return false;
        }
        if (!Objects.equals(this.roleCode, other.roleCode)) {
            return false;
        }
        if (!Objects.equals(this.deptCode, other.deptCode)) {
            return false;
        }
        return true;
    }

}
