/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity.temp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author winswe
 */
@Entity
@Table(name="tmp_profit_lost")
public class TmpProfitAndLost implements java.io.Serializable {
    private Long tranId;
    private String groupDesp;
    private String accId;
    private String accName;
    private String currency;
    private Double accTotal;
    private String userCode;
    private Integer compCode;
    private Integer sortOrder;

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tran_id", unique = true, nullable = false)
    public Long getTranId() {
        return tranId;
    }

    public void setTranId(Long tranId) {
        this.tranId = tranId;
    }

    @Column(name="group_desp", length=255)
    public String getGroupDesp() {
        return groupDesp;
    }

    public void setGroupDesp(String groupDesp) {
        this.groupDesp = groupDesp;
    }

    @Column(name="acc_id", length=15)
    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    @Column(name="acc_name", length=255)
    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    @Column(name="curr_id", length=15)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name="acc_total")
    public Double getAccTotal() {
        return accTotal;
    }

    public void setAccTotal(Double accTotal) {
        this.accTotal = accTotal;
    }

    @Column(name="user_code", length=15)
    public String getUserId() {
        return userCode;
    }

    public void setUserId(String userCode) {
        this.userCode = userCode;
    }

    @Column(name="comp_code")
    public Integer getCompId() {
        return compCode;
    }

    public void setCompId(Integer compCode) {
        this.compCode = compCode;
    }

    @Column(name="sort_order")
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
