/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity;

import javax.persistence.*;

/**
 *
 * Customer class. Sharing "trader" table with Supplier, Patient and Trader
 * class. Database table name is trader.
 */
@Entity
//@Table(name="trader")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ST")
public class Staff extends Trader implements java.io.Serializable {

    public Staff() {
        super();
    }
}
