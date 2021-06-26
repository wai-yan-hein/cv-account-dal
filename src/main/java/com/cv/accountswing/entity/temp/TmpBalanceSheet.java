/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity.temp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author winswe
 */
@Entity
@Table(name = "tmp_balance_sheet")
public class TmpBalanceSheet implements java.io.Serializable {

    @Id
    @Column(name = "acc_code", unique = true, nullable = false)
    private String accCode;
    @Column(name = "curr_code")
    private String currency;
    @Column(name = "acc_total")
    private Double accTotal;
    @Column(name = "comp_code")
    private String compCode;
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Column(name = "dept_code")
    private String depCode;
    @Column(name = "mac_id")
    private Integer macId;

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public Double getAccTotal() {
        return accTotal;
    }

    public void setAccTotal(Double accTotal) {
        this.accTotal = accTotal;
    }

    public Integer getMacId() {
        return macId;
    }

    public void setMacId(Integer macId) {
        this.macId = macId;
    }
    

}
