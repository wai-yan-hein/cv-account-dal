/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity.view;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author winswe
 */
@Entity
@Table(name = "v_apar")
public class VApar implements java.io.Serializable {

    private VAparKey key;
    private String traderId;
    private String traderName;
    private Double opening;
    private Double drAmt;
    private Double crAmt;
    private Double closing;
    private String accountCode;

    @EmbeddedId
    public VAparKey getKey() {
        return key;
    }

    public void setKey(VAparKey key) {
        this.key = key;
    }

    @Column(name = "trader_id")
    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    @Column(name = "trader_name")
    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    @Column(name = "opening")
    public Double getOpening() {
        return opening;
    }

    public void setOpening(Double opening) {
        this.opening = opening;
    }

    @Column(name = "dr_amt")
    public Double getDrAmt() {
        return drAmt;
    }

    public void setDrAmt(Double drAmt) {
        this.drAmt = drAmt;
    }

    @Column(name = "cr_amt")
    public Double getCrAmt() {
        return crAmt;
    }

    public void setCrAmt(Double crAmt) {
        this.crAmt = crAmt;
    }

    @Column(name = "closing")
    public Double getClosing() {
        return closing;
    }

    public void setClosing(Double closing) {
        this.closing = closing;
    }

    @Column(name = "account_code")
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

}
