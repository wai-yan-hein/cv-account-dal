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
 * @author SAI
 */
@Embeddable
public class SeqKey implements Serializable {

    private Integer macId;
    private String seqOption;
    private String period;
    private String compCode;

    @Column(name = "mac_id")
    public Integer getMacId() {
        return macId;
    }

    public void setMacId(Integer macId) {
        this.macId = macId;
    }

    @Column(name = "seq_option")
    public String getSeqOption() {
        return seqOption;
    }

    public void setSeqOption(String seqOption) {
        this.seqOption = seqOption;
    }

    @Column(name = "period")
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Column(name = "comp_code")
    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

}
