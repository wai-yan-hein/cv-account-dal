/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dummy;

import java.util.Date;

/**
 *
 * @author SAI
 */
public class PurVouSearchFilter {

    private Date tranDate;
    private String vouNo;
    private String remark;
    private String traderName;
    private Float vouTotal;
    private boolean deleted;
    private String userName;

    public PurVouSearchFilter(Date tranDate, String vouNo, String remark, String traderName, Float vouTotal, boolean deleted, String userName) {
        this.tranDate = tranDate;
        this.vouNo = vouNo;
        this.remark = remark;
        this.traderName = traderName;
        this.vouTotal = vouTotal;
        this.deleted = deleted;
        this.userName = userName;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public String getVouNo() {
        return vouNo;
    }

    public void setVouNo(String vouNo) {
        this.vouNo = vouNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public Float getVouTotal() {
        return vouTotal;
    }

    public void setVouTotal(Float vouTotal) {
        this.vouTotal = vouTotal;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
