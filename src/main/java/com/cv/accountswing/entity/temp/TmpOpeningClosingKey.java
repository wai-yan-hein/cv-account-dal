/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity.temp;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author winswe
 */
@Embeddable
public class TmpOpeningClosingKey implements Serializable {

    private String coaId;
    private String currId;
    private String userCode;
    private Integer macId;

    public TmpOpeningClosingKey() {
    }

    @Column(name = "coa_code", nullable = false, length = 25)
    public String getCoaId() {
        return coaId;
    }

    public void setCoaId(String coaId) {
        this.coaId = coaId;
    }

    @Column(name = "curr_id", nullable = false, length = 15)
    public String getCurrId() {
        return currId;
    }

    public void setCurrId(String currId) {
        this.currId = currId;
    }

    @Column(name = "user_code", nullable = false, length = 15)

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Column(name = "mac_id")
    public Integer getMacId() {
        return macId;
    }

    public void setMacId(Integer macId) {
        this.macId = macId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.coaId);
        hash = 67 * hash + Objects.hashCode(this.currId);
        hash = 67 * hash + Objects.hashCode(this.userCode);
        hash = 67 * hash + Objects.hashCode(this.macId);
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
        final TmpOpeningClosingKey other = (TmpOpeningClosingKey) obj;
        if (!Objects.equals(this.coaId, other.coaId)) {
            return false;
        }
        if (!Objects.equals(this.currId, other.currId)) {
            return false;
        }
        if (!Objects.equals(this.userCode, other.userCode)) {
            return false;
        }
        if (!Objects.equals(this.macId, other.macId)) {
            return false;
        }
        return true;
    }

}
