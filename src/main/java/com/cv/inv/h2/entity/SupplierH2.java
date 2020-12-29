/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity;

import com.cv.accountswing.entity.*;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * Patient class is patient information. Sharing "trader" table with Patient, 
 * Customer and Trader class.
 * Database table name is trader.
 */
@Entity
@Table(name="trader_h2")
@DiscriminatorValue("S")
public class SupplierH2 extends TraderH2 implements java.io.Serializable{

    public SupplierH2() {
    }
    
    public SupplierH2(Integer id, String traderName) {
        super(id, traderName);
    }
    
}
