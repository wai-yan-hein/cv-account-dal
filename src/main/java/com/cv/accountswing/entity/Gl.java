/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author WSwe
 */
@Entity
@Table(name = "gl")
public class Gl implements java.io.Serializable {

    @Id
    @Column(name = "gl_code", unique = true, nullable = false)
    private String glCode;
    @Temporal(TemporalType.DATE)
    @Column(name = "gl_date")
    private Date glDate;
    @Column(name = "description", length = 255)
    private String description;
    @Column(name = "source_ac_id")
    private String sourceAcId;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "to_cur_id")
    private String toCurId;
    @Column(name = "from_cur_id")
    private String fromCurId;
    @Column(name = "ex_rate")
    private Float exRate;
    @Column(name = "dr_amt")
    private Float drAmt;
    @Column(name = "cr_amt")
    private Float crAmt;
    @Column(name = "reference", length = 50)
    private String reference;
    @Column(name = "dept_code")
    private String deptId;
    @Column(name = "voucher_no", length = 15)
    private String vouNo;
    @Column(name = "trader_code")
    private String traderCode;
    @Column(name = "cheque_no", length = 20)
    private String chequeNo;
    @Column(name = "comp_code")
    private String compCode;
    @Column(name = "gst")
    private Float gst;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    @Column(name = "modify_by", length = 15)
    private String modifyBy;
    @Column(name = "user_code", length = 15)
    private String createdBy;
    @Column(name = "tran_source", length = 25)
    private String tranSource;
    @Column(name = "bank_code")
    private String bankCode;
    @Column(name = "gl_vou_no", length = 25)
    private String glVouNo; //For general voucher system id
    @Column(name = "split_id")
    private Integer splitId;
    @Column(name = "intg_upd_status", length = 5)
    private String intgUpdStatus; //For integration update status
    @Column(name = "remark", length = 500)
    private String remark;
    @Column(name = "from_desp", length = 500)
    private String fromDesp;
    @Column(name = "to_desp", length = 500)
    private String toDesp;
    @Column(name = "naration", length = 500)
    private String naration;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "mac_id")
    private Integer macId;
    @Column(name = "ref_no")
    private String refNo;

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceAcId() {
        return sourceAcId;
    }

    public void setSourceAcId(String sourceAcId) {
        this.sourceAcId = sourceAcId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToCurId() {
        return toCurId;
    }

    public void setToCurId(String toCurId) {
        this.toCurId = toCurId;
    }

    public String getFromCurId() {
        return fromCurId;
    }

    public void setFromCurId(String fromCurId) {
        this.fromCurId = fromCurId;
    }

    public Float getExRate() {
        return exRate;
    }

    public void setExRate(Float exRate) {
        this.exRate = exRate;
    }

    public Float getDrAmt() {
        return drAmt;
    }

    public void setDrAmt(Float drAmt) {
        this.drAmt = drAmt;
    }

    public Float getCrAmt() {
        return crAmt;
    }

    public void setCrAmt(Float crAmt) {
        this.crAmt = crAmt;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getVouNo() {
        return vouNo;
    }

    public void setVouNo(String vouNo) {
        this.vouNo = vouNo;
    }

    public String getTraderCode() {
        return traderCode;
    }

    public void setTraderCode(String traderCode) {
        this.traderCode = traderCode;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Float getGst() {
        return gst;
    }

    public void setGst(Float gst) {
        this.gst = gst;
    }

    public String getTranSource() {
        return tranSource;
    }

    public void setTranSource(String tranSource) {
        this.tranSource = tranSource;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getGlVouNo() {
        return glVouNo;
    }

    public void setGlVouNo(String glVouNo) {
        this.glVouNo = glVouNo;
    }

    public Integer getSplitId() {
        return splitId;
    }

    public void setSplitId(Integer splitId) {
        this.splitId = splitId;
    }

    public String getIntgUpdStatus() {
        return intgUpdStatus;
    }

    public void setIntgUpdStatus(String intgUpdStatus) {
        this.intgUpdStatus = intgUpdStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFromDesp() {
        return fromDesp;
    }

    public void setFromDesp(String fromDesp) {
        this.fromDesp = fromDesp;
    }

    public String getToDesp() {
        return toDesp;
    }

    public void setToDesp(String toDesp) {
        this.toDesp = toDesp;
    }

    public String getNaration() {
        return naration;
    }

    public void setNaration(String naration) {
        this.naration = naration;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getMacId() {
        return macId;
    }

    public void setMacId(Integer macId) {
        this.macId = macId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

}
