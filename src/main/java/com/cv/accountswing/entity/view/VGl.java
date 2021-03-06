/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity.view;

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
@Table(name = "v_gl")
public class VGl implements java.io.Serializable {

    private String glCode;
    private Date glDate;
    private String description;
    private String sourceAcId;
    private String accountId;
    private String toCurId;
    private String fromCurId;
    private Double exRate;
    private Double drAmt;
    private Double crAmt;
    private String reference;
    private String deptId;
    private String vouNo;
    private String traderCode;
    private String chequeNo;
    private String compCode;
    private Double gst;
    private Date createdDate;
    private Date modifyDate;
    private String modifyBy;
    private String createdBy;
    private String tranSource;
    private String bankCode;
    private String srcAccName;
    private String accName;
    private String fCurName;
    private String tCurName;
    private String deptName;
    private String deptUsrCode;
    private String bankName;
    private String traderName;
    private String traderType;
    private String glVouNo;
    private String srcAccCode;
    private String accCode;
    private Integer splitId;
    private String sourceAccParent;
    private String accParent;
    private String remark;
    private String fromDesp;
    private String toDesp;
    private String naration;
    private Long projectId;
    private Integer macId;
    private String refNo;

    public VGl() {
    }

    @Id
    @Column(name = "gl_code", unique = true, nullable = false)
    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "gl_date")
    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    @Column(name = "description", length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "source_ac_id")
    public String getSourceAcId() {
        return sourceAcId;
    }

    public void setSourceAcId(String sourceAcId) {
        this.sourceAcId = sourceAcId;
    }

    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Column(name = "to_cur_id")
    public String getToCurId() {
        return toCurId;
    }

    public void setToCurId(String toCurId) {
        this.toCurId = toCurId;
    }

    @Column(name = "from_cur_id")
    public String getFromCurId() {
        return fromCurId;
    }

    public void setFromCurId(String fromCurId) {
        this.fromCurId = fromCurId;
    }

    @Column(name = "ex_rate")
    public Double getExRate() {
        return exRate;
    }

    public void setExRate(Double exRate) {
        this.exRate = exRate;
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

    @Column(name = "reference", length = 50)
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Column(name = "dept_code")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(name = "voucher_no", length = 15)
    public String getVouNo() {
        return vouNo;
    }

    public void setVouNo(String vouNo) {
        this.vouNo = vouNo;
    }

    @Column(name = "trader_code")
    public String getTraderCode() {
        return traderCode;
    }

    public void setTraderCode(String traderCode) {
        this.traderCode = traderCode;
    }

    @Column(name = "cheque_no", length = 20)
    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    @Column(name = "comp_code")
    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "modify_by", length = 15)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Column(name = "user_code", length = 15)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "gst")
    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    @Column(name = "tran_source", length = 25)
    public String getTranSource() {
        return tranSource;
    }

    public void setTranSource(String tranSource) {
        this.tranSource = tranSource;
    }

    @Column(name = "bank_code")
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Column(name = "source_acc_name")
    public String getSrcAccName() {
        return srcAccName;
    }

    public void setSrcAccName(String srcAccName) {
        this.srcAccName = srcAccName;
    }

    @Column(name = "acc_name")
    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    @Column(name = "fcur_name")
    public String getfCurName() {
        return fCurName;
    }

    public void setfCurName(String fCurName) {
        this.fCurName = fCurName;
    }

    @Column(name = "tcur_name")
    public String gettCurName() {
        return tCurName;
    }

    public void settCurName(String tCurName) {
        this.tCurName = tCurName;
    }

    @Column(name = "dept_name")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name = "usr_code")
    public String getDeptUsrCode() {
        return deptUsrCode;
    }

    public void setDeptUsrCode(String deptUsrCode) {
        this.deptUsrCode = deptUsrCode;
    }

    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "trader_name")
    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    @Column(name = "discriminator")
    public String getTraderType() {
        return traderType;
    }

    public void setTraderType(String traderType) {
        this.traderType = traderType;
    }

    @Column(name = "gl_vou_no")
    public String getGlVouNo() {
        return glVouNo;
    }

    public void setGlVouNo(String glVouNo) {
        this.glVouNo = glVouNo;
    }

    @Column(name = "source_acc_code")
    public String getSrcAccCode() {
        return srcAccCode;
    }

    public void setSrcAccCode(String srcAccCode) {
        this.srcAccCode = srcAccCode;
    }

    @Column(name = "acc_code")
    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    @Column(name = "split_id")
    public Integer getSplitId() {
        return splitId;
    }

    public void setSplitId(Integer splitId) {
        this.splitId = splitId;
    }

    @Column(name = "source_acc_parent")
    public String getSourceAccParent() {
        return sourceAccParent;
    }

    public void setSourceAccParent(String sourceAccParent) {
        this.sourceAccParent = sourceAccParent;
    }

    @Column(name = "acc_parent")
    public String getAccParent() {
        return accParent;
    }

    public void setAccParent(String accParent) {
        this.accParent = accParent;
    }

    @Column(name = "remark", length = 500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "from_desp", length = 500)
    public String getFromDesp() {
        return fromDesp;
    }

    public void setFromDesp(String fromDesp) {
        this.fromDesp = fromDesp;
    }

    @Column(name = "to_desp", length = 500)
    public String getToDesp() {
        return toDesp;
    }

    public void setToDesp(String toDesp) {
        this.toDesp = toDesp;
    }

    @Column(name = "naration", length = 500)
    public String getNaration() {
        return naration;
    }

    public void setNaration(String naration) {
        this.naration = naration;
    }

    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Column(name = "mac_id")
    public Integer getMacId() {
        return macId;
    }

    public void setMacId(Integer macId) {
        this.macId = macId;
    }

    @Column(name = "ref_no")
    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

}

