/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sai
 */
@Embeddable
public class SaleDetailKeyH2 implements Serializable {

    private String vouId;
    private String saleDetailId;

    public SaleDetailKeyH2() {
    }

    public SaleDetailKeyH2(String vouId, String saleDetailId) {
        this.vouId = vouId;
        this.saleDetailId = saleDetailId;
    }

    @Column(name = "vou_id", length = 15)
    public String getVouId() {
        return vouId;
    }

    public void setVouId(String vouId) {
        this.vouId = vouId;
    }

    @Column(name = "sale_detail_id", length = 20)
    public String getSaleDetailId() {
        return saleDetailId;
    }

    public void setSaleDetailId(String saleDetailId) {
        this.saleDetailId = saleDetailId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.vouId);
        hash = 89 * hash + Objects.hashCode(this.saleDetailId);
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
        final SaleDetailKeyH2 other = (SaleDetailKeyH2) obj;
        if (!Objects.equals(this.vouId, other.vouId)) {
            return false;
        }
        if (!Objects.equals(this.saleDetailId, other.saleDetailId)) {
            return false;
        }
        return true;
    }
    

}
