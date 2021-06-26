/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity.view;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "v_opening")
public class VCOAOpening implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "coa_op_id", unique = true, nullable = false)
    private Integer opId;
    @Temporal(TemporalType.DATE)
    @Column(name = "op_date")
    private Date opDate;
    @Column(name = "source_acc_id")
    private String sourceAccId;
    @Column(name = "coa_name_eng")
    private String sourceAccName;
    @Column(name = "cur_code")
    private String curCode;
    @Column(name = "cr_amt")
    private Double crAmt;
    @Column(name = "dr_amt")
    private Double drAmt;
    @Column(name = "user_code")
    private String userCode;
    @Column(name = "comp_code")
    private String compCode;
    @Column(name = "dep_usr_code")
    private String depUsrCode;
    @Column(name = "trader_code")
    private String traderCode;
    @Column(name = "trader_name")
    private String traderName;
    @Column(name = "dept_code")
    private String depCode;
    @Column(name = "trader_usr_code")
    private String traderUsrCode;
    @Column(name = "trader_type")
    private String traderType;
    @Column(name = "coa_parent")
    private String coaParent;
    @Column(name = "reg_code")
    private String regCode;
    @Column(name = "coa_code_usr")
    private String coaUsrCode;

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getSourceAccId() {
        return sourceAccId;
    }

    public void setSourceAccId(String sourceAccId) {
        this.sourceAccId = sourceAccId;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public Double getCrAmt() {
        return crAmt;
    }

    public void setCrAmt(Double crAmt) {
        this.crAmt = crAmt;
    }

    public Double getDrAmt() {
        return drAmt;
    }

    public void setDrAmt(Double drAmt) {
        this.drAmt = drAmt;
    }

   

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getSourceAccName() {
        return sourceAccName;
    }

    public void setSourceAccName(String sourceAccName) {
        this.sourceAccName = sourceAccName;
    }

    public String getDepUsrCode() {
        return depUsrCode;
    }

    public void setDepUsrCode(String depUsrCode) {
        this.depUsrCode = depUsrCode;
    }

    public String getTraderCode() {
        return traderCode;
    }

    public void setTraderCode(String traderCode) {
        this.traderCode = traderCode;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getTraderUsrCode() {
        return traderUsrCode;
    }

    public void setTraderUsrCode(String traderUsrCode) {
        this.traderUsrCode = traderUsrCode;
    }

    public String getTraderType() {
        return traderType;
    }

    public void setTraderType(String traderType) {
        this.traderType = traderType;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCoaParent() {
        return coaParent;
    }

    public void setCoaParent(String coaParent) {
        this.coaParent = coaParent;
    }

    public String getCoaUsrCode() {
        return coaUsrCode;
    }

    public void setCoaUsrCode(String coaUsrCode) {
        this.coaUsrCode = coaUsrCode;
    }

}
