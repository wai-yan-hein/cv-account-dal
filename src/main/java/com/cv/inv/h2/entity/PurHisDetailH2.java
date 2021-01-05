/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity;

import com.cv.inv.entity.*;
import com.cv.accountswing.entity.Department;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "pur_his_detail_h2")
public class PurHisDetailH2 implements Serializable {

    @EmbeddedId
    private PurDetailKeyH2 purDetailKey;
    @ManyToOne
    @JoinColumn(name = "stock_code", nullable = false)
    private StockH2 stock;
    @Column(name = "qty", nullable = false)
    private Float qty;
    @Column(name = "std_wt", nullable = false)
    private Float stdWeight;
    @ManyToOne
    @JoinColumn(name = "item_unit_code", nullable = false)
    private StockUnitH2 purUnit;
    @Column(name = "avg_wt")
    private Float avgWeight;
    @Column(name = "avg_price")
    private Float avgPrice;
    @Column(name = "pur_price", nullable = false)
    private Float purPrice;
    @Column(name = "pur_amt", nullable = false)
    private Float purAmt;
    @ManyToOne
    @JoinColumn(name = "loc_id")
    private LocationH2 location;
    @Column(name = "unique_id")
    private Integer uniqueId;
    @Column(name = "gl_id")
    private Long glId;
    @Column(name = "small_wt")
    private Float smallestWT;
    @Column(name = "small_unit")
    private String smallestUnit;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private DepartmentH2 department;
    @Column(name = "std_small_wt")
    private Float stdSmallWeight;

    public PurDetailKeyH2 getPurDetailKey() {
        return purDetailKey;
    }

    public void setPurDetailKey(PurDetailKeyH2 purDetailKey) {
        this.purDetailKey = purDetailKey;
    }

    public StockH2 getStock() {
        return stock;
    }

    public void setStock(StockH2 stock) {
        this.stock = stock;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Float getStdWeight() {
        return stdWeight;
    }

    public void setStdWeight(Float stdWeight) {
        this.stdWeight = stdWeight;
    }

    public StockUnitH2 getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(StockUnitH2 purUnit) {
        this.purUnit = purUnit;
    }

    public Float getAvgWeight() {
        return avgWeight;
    }

    public void setAvgWeight(Float avgWeight) {
        this.avgWeight = avgWeight;
    }

    public Float getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(Float purPrice) {
        this.purPrice = purPrice;
    }

    public Float getPurAmt() {
        return purAmt;
    }

    public void setPurAmt(Float purAmt) {
        this.purAmt = purAmt;
    }

    public LocationH2 getLocation() {
        return location;
    }

    public void setLocation(LocationH2 location) {
        this.location = location;
    }

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Long getGlId() {
        return glId;
    }

    public void setGlId(Long glId) {
        this.glId = glId;
    }

    public Float getSmallestWT() {
        return smallestWT;
    }

    public void setSmallestWT(Float smallestWT) {
        this.smallestWT = smallestWT;
    }

    public String getSmallestUnit() {
        return smallestUnit;
    }

    public void setSmallestUnit(String smallestUnit) {
        this.smallestUnit = smallestUnit;
    }

    public Float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
    }

    public DepartmentH2 getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentH2 department) {
        this.department = department;
    }

    public Float getStdSmallWeight() {
        return stdSmallWeight;
    }

    public void setStdSmallWeight(Float stdSmallWeight) {
        this.stdSmallWeight = stdSmallWeight;
    }
    

}
