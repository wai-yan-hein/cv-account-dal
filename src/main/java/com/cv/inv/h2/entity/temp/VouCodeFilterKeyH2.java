/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity.temp;


import com.cv.inv.h2.entity.StockH2;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author lenovo
 */
@Embeddable
public class VouCodeFilterKeyH2 implements Serializable {

    private String tranOption;
    private String userId;
    private StockH2 itemCode;

    public VouCodeFilterKeyH2() {
    }

    public VouCodeFilterKeyH2(String tranOption, String userId, StockH2 itemCode) {
        this.tranOption = tranOption;
        this.userId = userId;
        this.itemCode = itemCode;
    }

    @Column(name = "tran_option", nullable = false, length = 20)
    public String getTranOption() {
        return tranOption;
    }

    public void setTranOption(String tranOption) {
        this.tranOption = tranOption;
    }

    @Column(name = "user_id", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    public StockH2 getItemCode() {
        return itemCode;
    }

    public void setItemCode(StockH2 itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.tranOption);
        hash = 89 * hash + Objects.hashCode(this.userId);
        hash = 89 * hash + Objects.hashCode(this.itemCode);
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
        final VouCodeFilterKeyH2 other = (VouCodeFilterKeyH2) obj;
        if (!Objects.equals(this.tranOption, other.tranOption)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.itemCode, other.itemCode)) {
            return false;
        }
        return true;
    }
}
