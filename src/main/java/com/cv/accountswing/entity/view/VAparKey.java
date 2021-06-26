/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author winswe
 */
@Embeddable
public class VAparKey implements Serializable {

    private String traderCode;
    private Integer macId;
    private String compCode;
    private String currency;

    @Column(name = "trader_code")
    public String getTraderCode() {
        return traderCode;
    }

    public void setTraderCode(String traderCode) {
        this.traderCode = traderCode;
    }

    @Column(name = "curr_id")
    public String getCurrency() {
        return currency;
    }

    @Column(name = "mac_id")
    public Integer getMacId() {
        return macId;
    }

    public void setMacId(Integer macId) {
        this.macId = macId;
    }

    @Column(name = "comp_code")
    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
