/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.entity;

import com.cv.accountswing.entity.ChartOfAccount;
import com.cv.accountswing.entity.Department;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity()
@Table(name = "acc_setting")
public class AccSetting {

    @Id
    @Column(name = "type", unique = true, nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "dis_acc")
    private ChartOfAccount disAccount;
    @ManyToOne
    @JoinColumn(name = "pay_acc")
    private ChartOfAccount payAccount;
    @ManyToOne
    @JoinColumn(name = "tax_acc")
    private ChartOfAccount taxAccount;
    @ManyToOne
    @JoinColumn(name = "dep_code")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "source_acc")
    private ChartOfAccount soureAccount;

    public ChartOfAccount getDisAccount() {
        return disAccount;
    }

    public void setDisAccount(ChartOfAccount disAccount) {
        this.disAccount = disAccount;
    }

    public ChartOfAccount getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(ChartOfAccount payAccount) {
        this.payAccount = payAccount;
    }

    public ChartOfAccount getTaxAccount() {
        return taxAccount;
    }

    public void setTaxAccount(ChartOfAccount taxAccount) {
        this.taxAccount = taxAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ChartOfAccount getSoureAccount() {
        return soureAccount;
    }

    public void setSoureAccount(ChartOfAccount soureAccount) {
        this.soureAccount = soureAccount;
    }

}
